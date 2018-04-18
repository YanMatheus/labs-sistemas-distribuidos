/*
defines the interface that is used by the
client and implemented by the server.

Interface Definition Language
*/
package shared;

public interface RmiServerIntf extends java.rmi.Remote {
    public String getMessage(int x)
      throws java.rmi.RemoteException;
}
