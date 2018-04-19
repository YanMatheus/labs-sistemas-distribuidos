package rmiserver;

import shared.*;
import java.rmi.RemoteException;

/**
 * A simple implementation of a printing interface.
 */
public class PrintingInterfaceImpl implements PrintingInterface {

    @Override
    public int echoMessage(ClientInterface autor, String msg) throws RemoteException {
        System.out.println("Got a message from the client: " + msg);
        autor.setName("alteradoo");
        return msg.length();
    }

}
