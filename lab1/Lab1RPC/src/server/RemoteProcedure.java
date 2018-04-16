package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import shared.RPCMetaData;

@FunctionalInterface
interface RunnableRemoteProcedure {
    void run(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException;
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
    static void doDesvioPadrao(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException {
        ArrayList<Object> args = rmd.getArgs();

        List<Double> values = (List<Double>) args.get(0);
        InfoLog.printToStdout("client called RPC desvioPadrao(...{%d})", values.size());

        // Realizar a operação do procedimento `desvioPadrao`
        Stream<Double> valuesAsStream = values.stream();
        double mean = valuesAsStream.collect( Collectors.averagingDouble(Double::doubleValue) );
        double numerador = 0d;
        for (Double value : values) numerador += Math.pow(value - mean, 2);
        Double result = Math.sqrt(numerador / ((double) values.size() - 1));

        // [send] Escrever o resultado do procedimento `desvioPadrao`
        out.writeDouble(result);
        out.flush();
        InfoLog.printToStdout("sended %s '%f' to client", "Double", result);
    }



}