package client.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.RPCMetaData;

/**
 * Representa uma conexão do client com o server.
 * Usa-se sockets stream para a troca de mensagens
 * entre dois hosts remotos.
 *
 * @author micael
 */
public class SocketController {

    private Socket cs;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public Double callRPDesvioPadrao(List<Double> valores) throws IOException {
        // Definição dos argumentos para a função remota `DesvioPadrao`
        ArrayList<Object> RPArgs = new ArrayList<>(1);
        RPArgs.add(0, valores);

        // Construção do objeto que será recebido pelo servidor
        RPCMetaData rmd = new RPCMetaData(RPCMetaData.ID_RP_DESVIOPADRAO, RPArgs);

        // [send] Escrita do objeto que representa os meta-dados da RP
        this.out.writeObject(rmd);
        System.out.printf("[send] called desvio_padrao(...{%d})\n", ((ArrayList)RPArgs.get(0)).size());

        // [receive] Leitura da confirmação do server sobre se a RP foi encontrada
        Boolean found = this.in.readBoolean();

        if (found) {
            System.out.printf("[waiting] server RPC response\n");
            // [receive] Leitura do resultado obtido da chamada da RP
            double result = this.in.readDouble();
            System.out.printf("[receive] RPC result: %f\n", result);
            return result;
        }

        return null;
    }



    /**
     *
     * @param serverHost - the server host name, or <code>null</code> for the loopback address.
     * @param serverPort - the port number which server host is listening.
     * @throws IOException
     */
    public SocketController(String serverHost, int serverPort) throws IOException {
        // Conectar com um processo remoto
        cs = new Socket(serverHost, serverPort);
        System.out.printf("Socket cliente criado com endereço '%s' conectado com socket '%s'\n",
            cs.getLocalSocketAddress(), cs.getRemoteSocketAddress());

        // Abrir um canal para a troca de mensagens
        in = new ObjectInputStream( cs.getInputStream() );
        out = new ObjectOutputStream( cs.getOutputStream() );
    }

    /**
     * Tenta fechar o socket criado
     * assim como os fluxos de dados
     * de entrada e saída.
     */
    public void closeSocket() {
        try {

            this.in.close();
            this.out.close();
            this.cs.close();

        } catch (IOException ex) {
            Logger.getLogger(SocketController.class.getName())
                  .log(Level.SEVERE, "Erro de I/O ao fechar client socket", ex);
        }
    }

}
