package rmiclient;

import shared.PrintingInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientMain {

    public static void main(String[] args) {
        // Set the Security Manager that we want to use.
        // The Security Manager must be set, or it will not work.
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String serviceName = "RMI-EchoMessage";
            // Registry registry = LocateRegistry.getRegistry("10.208.200.172", 6789);
            Registry registry = LocateRegistry.getRegistry("localhost", 6789);
            PrintingInterface comp = (PrintingInterface) registry.lookup(serviceName);

            System.out.println("About to try to print!");

            String messageToEcho = "Hi from the client!";
            if( args.length > 0 ) messageToEcho = args[0];

            ClientInterfaceImpl c = new ClientInterfaceImpl("joão byte");
            int returnVal = comp.echoMessage(c, messageToEcho);

            System.out.println( "The return value from the server is (msg length): " + returnVal );
            System.out.println("client.name: " + c.getName());
        } catch (Exception e) {
            System.err.println( "Exception while trying to echo:" );
            e.printStackTrace();
        }
    }
}
