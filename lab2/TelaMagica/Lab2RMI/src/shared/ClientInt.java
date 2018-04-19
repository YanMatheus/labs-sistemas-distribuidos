package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Micael
 */
public interface ClientInt extends Remote {
    public void updateText(String text) throws RemoteException;
    public String getNickname() throws java.rmi.RemoteException;
    public void addListaView(String nickname) throws java.rmi.RemoteException;
    public void removeFromListaView(String nickname) throws java.rmi.RemoteException;
}
