/*
this is the client which gets the reference (a proxy) to the remote object living
on the server and invokes its method to get a message. If the server object
implemented java.io.Serializable instead of java.rmi.Remote,
it would be serialized and passed to the client as a value.
*/
package client;

import java.rmi.Naming;
import shared.*;

public class RmiClient {
    public static void main(String args[]) throws Exception {

        try {
          RmiServerIntf obj = (RmiServerIntf)
            Naming.lookup("rmi://localhost:1099/RmiServer");
            //             ^^^ scheme component opcional


          try {
            String result = obj.getMessage(3);
            System.out.printf("<< obj.getMessage(3)%n>> %s%n", result);
          } catch (java.rmi.RemoteException e) { e.printStackTrace(); }
        } catch (java.net.MalformedURLException e) { e.printStackTrace(); }
    }
}
