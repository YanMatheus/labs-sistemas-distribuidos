package shared;

/**
 *
 * @author Micael
 */
public interface ServerAccountInt extends java.rmi.Remote {
    public static final String SERVICE_ACCOUNT = "ContaCompartilhada";

    public void connect(ClientAccountInt ca) throws java.rmi.RemoteException;
    public void disconnect(ClientAccountInt ca) throws java.rmi.RemoteException;
    public void broadcastBalance() throws java.rmi.RemoteException;
    public void broadcastClientList() throws java.rmi.RemoteException;

    public double showBalance() throws java.rmi.RemoteException;
    public void deposit(String nickname, double amount) throws java.rmi.RemoteException;
    public void withdraw(String nickname, double amount) throws java.rmi.RemoteException;
}
