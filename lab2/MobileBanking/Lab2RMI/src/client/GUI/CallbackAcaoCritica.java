package client.GUI;

/**
 *
 * @author Micael
 */
public interface CallbackAcaoCritica {
    void call(double quantia) throws java.rmi.RemoteException,
                                     shared.BusyOperationException;
}
