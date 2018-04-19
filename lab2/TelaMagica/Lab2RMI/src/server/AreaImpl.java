package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import shared.AreaInt;
import shared.ClientInt;

/**
 *
 * @author Micael
 */
public class AreaImpl implements AreaInt {

    private List<ClientInt> clients;
    private String text;

    public AreaImpl() throws RemoteException {
        this.clients = new ArrayList<>();
        this.text = null;
    }


    public void broadcast() throws RemoteException {
        for (ClientInt client : clients) client.updateText(text);
    }

    public void broadcastAddListView(String nickname) throws RemoteException {
        for (ClientInt client : clients) client.addListaView(nickname);
    }

    public void broadcastRemoveListView(String nickname) throws RemoteException {
        for (ClientInt client : clients) client.removeFromListaView(nickname);
    }

    @Override
    public void connect(ClientInt c) throws RemoteException {
        clients.add(c);
        if (text != null)
            c.updateText(text);
        broadcastAddListView(c.getNickname());
        InfoLog.printToStdout("cliente com nick '%s' entrou [%d]", c.getNickname(), clients.size());
    }

    @Override
    public void disconnect(ClientInt c) throws RemoteException {
        clients.remove(c);
        broadcastRemoveListView(c.getNickname());
        InfoLog.printToStdout("o cliente '%s' saiu", c.getNickname());
    }

    @Override
    public void setText(String text) throws RemoteException {
        this.text = text;
        broadcast();
    }

    @Override
    public void broadcastText(String text) throws RemoteException {
        for (ClientInt client : clients) client.updateText(text);
    }

}