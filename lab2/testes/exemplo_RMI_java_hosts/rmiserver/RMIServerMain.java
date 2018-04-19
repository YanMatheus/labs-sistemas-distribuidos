package rmiserver;

import shared.PrintingInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIServerMain {

    public static void main(String[] args) {
        // First, create the real object which will do the requested function.
        PrintingInterfaceImpl implementation = new PrintingInterfaceImpl();

        try {
            // Export the object.
            PrintingInterface stub = (PrintingInterface) UnicastRemoteObject.exportObject(implementation, 6789);

            // System.setProperty("java.rmi.server.hostname", "10.208.200.172");
            System.setProperty("java.rmi.server.hostname", "localhost");
            // System.setProperty("java.security.policy","file:///home/icomp/Área de Trabalho/Java-RMI-Example/server.policy");

            Registry registry = LocateRegistry.createRegistry(6789);
            // I don't know why we have to rebind at all.
            // However, this does set the string that you need to use in order to lookup the remote class.
            registry.rebind("RMI-EchoMessage", stub);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return;
        }
        System.out.println( "Bound!" );
        System.out.println( "Server will wait forever for messages." );
    }
}
