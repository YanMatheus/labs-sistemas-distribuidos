package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ServerAccountInt;

/**
 *
 * @author Micael
 */
public class ServerMain {

    public static void main(String[] args) {
        /*
        if (args.length != 1) {
            System.out.println("params: <server_port>");
            return;
        }

        // Porta para o socket deve estar entre 1024 a 65535
        int port = Integer.parseInt(args[0]);
        */
        int port = 6789;

        try {

            ServerAccountInt server = new Account();
            ServerAccountInt stub = (ServerAccountInt) UnicastRemoteObject.exportObject(server, port);

            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry r = LocateRegistry.createRegistry(port);
            r.rebind(ServerAccountInt.SERVICE_ACCOUNT, stub);

            InfoLog.printToStdout("server listenning at port %d...", port);

        } catch (RemoteException ex) {
            Logger.getLogger(ServerMain.class.getName())
                  .log(Level.SEVERE, "Erro ao iniciar o servidor", ex);
        }

    }

}
