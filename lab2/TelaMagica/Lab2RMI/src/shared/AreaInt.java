package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Micael
 */
public interface AreaInt extends Remote {
    public static final String SERVICE_AREA = "TelaMagica";

    public void connect(ClientInt c) throws RemoteException;
    public void disconnect(ClientInt c) throws RemoteException;

    public void setText(String text) throws RemoteException;
    public void broadcastText(String text) throws RemoteException;
}
