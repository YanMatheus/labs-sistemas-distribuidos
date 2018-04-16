package server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import shared.RPCMetaData;

/**
 *
 * @author micael
 */
public class MultiThreadedServer {

    /**
     * Define todas os procedimentos remotos
     * que estarão disponíveis para o processo cliente.
     *
     * @param m -
     */
    public static void setRemoteProcedures(Map<Short, RunnableRemoteProcedure> m) {
        m.put(RPCMetaData.ID_RP_DESVIOPADRAO, RemoteProcedure::doDesvioPadrao);
    }

    public static void main(String[] args) {
        // Porta para o socket deve estar entre 1024 a 65535
        int ssPort = Integer.parseInt(args[0]);
        final String PATH_TO_ROOT_DIR = args[1];

        Map<Short, RunnableRemoteProcedure> mapRP = new HashMap<>();
        setRemoteProcedures(mapRP);

        File rootDir = new File(PATH_TO_ROOT_DIR);
        long clientsAmount = 0;

        // Conectar o processo a um socket
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(ssPort);
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadedServer.class.getName())
                    .log(Level.SEVERE, "Erro socket bind", ex);
            System.exit(1);
        }

        InfoLog.printToStdout("server running at port %d", ssPort);

        do {
            Socket cs = null;

            try {
                cs = ss.accept();
                clientsAmount++;
            } catch (IOException ex) { break; }
                InfoLog.printToStdout("{%d} connection establish with '%s'",
                        clientsAmount, cs.getRemoteSocketAddress());

            // Inicia uma nova thread para tratar esse client (thread per connection)
            new ConnectionProtocol(cs, mapRP, rootDir);
        } while (true);
    }

}
