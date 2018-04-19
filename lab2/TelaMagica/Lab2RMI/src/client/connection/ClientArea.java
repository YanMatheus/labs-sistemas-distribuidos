package client.connection;

import client.GUI.MainWindow;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import shared.AreaInt;
import shared.ClientInt;

/**
 *
 * @author Micael
 */
public class ClientArea extends UnicastRemoteObject
        implements ClientInt {

    private static final long serialVersionUID = 1L;

    private AreaInt sa;
    private String nickname;
    private MainWindow mw;

    public ClientArea(AreaInt sa, MainWindow mw, String nickname) throws RemoteException {
        this.sa = sa;
        this.mw = mw;
        this.nickname = nickname;
        sa.connect(this);
    }


    public void callDisconnect() throws RemoteException {
        sa.disconnect(this);
    }

    public void callSetText(String text) throws RemoteException {
        sa.setText(text);
    }

    @Override
    public void updateText(String text) throws RemoteException {
        this.mw.atualizarTextArea(text);
    }

    @Override
    public String getNickname() throws RemoteException {
        return nickname;
    }

    @Override
    public void addListaView(String nickname) throws RemoteException {
        this.mw.addNaLista(nickname);
    }

    @Override
    public void removeFromListaView(String nickname) throws RemoteException {
        this.mw.removerDaLista(nickname);
    }

}
