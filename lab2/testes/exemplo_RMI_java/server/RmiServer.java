/*
listens to RMI requests and implements
the interface which is used by the client to invoke remote methods.
*/

package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import shared.*;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
    public final String MESSAGE = "Hello World";

    public RmiServer() throws RemoteException {
        super(0); // required to avoid the 'rmic' step, see below
    }

    public String getMessage(int x) {
        System.out.println("algum client executou o .getMessage");
        return this.MESSAGE + x;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { // special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            // do nothing, error means registry already exists
            // this will receive all request
            System.err.println("java RMI registry already exists.");
        }

        // Instantiate RmiServer
        RmiServer obj = new RmiServer();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("rmi://localhost:1099/RmiServer", obj);
        //             ^^^   ^^^^^^^^^ ^^^^ ^^^^^^^^^
        //             |     |         |    |
        //             |     |         |    +-- nome do serviço (service name)
        //             |     |         +-- número da porta (port number)
        //             |     +-- endereço da máquina em que o objeto reside (hostname)
        //             +-- (scheme component) opcional
        System.out.println("PeerServer bound in registry");
        System.out.println(obj);
    }
}
