package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import shared.RPCMetaData;

@FunctionalInterface
interface RunnableRemoteProcedure {
    void run(RPCMetaData rmd, ObjectOutputStream out, File rootDir) throws EOFException, IOException;
}

/**
 * Reune a implementação de todos
 * os procedimentos remotos disponíveis
 * pelo servidor.
 *
 * @author micael
 */
interface RemoteProcedure {

    /**
     * Realiza as ações para o
     * procedimento remoto `DesvioPadrao`.
     * @throws EOFException - if this input stream reaches the end before reading four bytes.
     * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
     */
    static void doDesvioPadrao(RPCMetaData rmd, ObjectOutputStream out, File rootDir) throws EOFException, IOException {
        ArrayList<Object> args = rmd.getArgs();

        List<Double> values = (List<Double>) args.get(0);
        InfoLog.printToStdout("client called RPC desvioPadrao(...{%d})", values.size());

        // Realizar a operação do procedimento `desvioPadrao`
        Stream<Double> valuesAsStream = values.stream();
        double mean = valuesAsStream.collect( Collectors.averagingDouble(Double::doubleValue) );
        double numerador = 0d;
        numerador = values.stream()
                .map(value -> Math.pow(value - mean, 2))
                .reduce(numerador, (accum, value) -> accum + value);
        Double result = Math.sqrt(numerador / ((double) values.size() - 1));

        // [send] Escrever o resultado do procedimento `desvioPadrao`
        out.writeDouble(result);
        out.flush();
        InfoLog.printToStdout("sent %s '%f' to client", "Double", result);
    }

    /**
     * Realiza as ações para o
     * procedimento remoto `BaixarDiretorio`.
     * @throws EOFException - if this input stream reaches the end before reading four bytes.
     * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
     */
    static void doBaixarDiretorio(RPCMetaData rmd, ObjectOutputStream out, File rootDir) throws EOFException, IOException {
        ArrayList<Object> args = rmd.getArgs();

        String dirOrigem = rootDir.getParent() + File.separator + ((String) args.get(0));
        InfoLog.printToStdout("client called RPC baixar_diretorio(...{%d})", 1);

        // Realizar a operação do procedimento `baixarDiretorio`
        final String dirOrigemZip = dirOrigem + ".zip";

        try {
            Zipper.zipFile(dirOrigem, dirOrigemZip);
        } catch (IOException ex) {
            ex.printStackTrace();
            out.writeLong(0L); // indicar erro
            out.flush();
            return;
        }

        try {

            File fileSent = new File(dirOrigemZip);
            byte[] buffer = new byte[ (int)fileSent.length() ];
            FileInputStream fis = new FileInputStream(fileSent);
            BufferedInputStream bis = new BufferedInputStream(fis);

            bis.read(buffer, 0, buffer.length);

            // [send] Escrever o tamanho do arquivo a ser enviado
            out.writeLong( fileSent.length() );
            // [send] Escrever o resultado do procedimento `desvioPadrao`
            out.write(buffer, 0, buffer.length);
            out.flush();
            InfoLog.printToStdout("sent '%s' (%d bytes) to client", fileSent.getName(), fileSent.length());

            bis.close();
            fileSent.delete();

      } catch (IOException ex) {
            ex.printStackTrace();
      }
    }


}
