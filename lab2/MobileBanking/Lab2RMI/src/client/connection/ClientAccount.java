package client.connection;

import client.GUI.MainWindow;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import shared.ClientAccountInt;
import shared.ServerAccountInt;


/**
 *
 * @author Micael
 */
public class ClientAccount extends UnicastRemoteObject
        implements ClientAccountInt {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private ServerAccountInt sa;
    private MainWindow mw;
    private List<ClientAccountInt> usuariosConectados;

    public ClientAccount(ServerAccountInt sa, MainWindow mw, String nickname) throws RemoteException {
        this.sa = sa;
        this.mw = mw;
        this.nickname = nickname;
        sa.connect(this);
    }

    @Override
    public void atualizarUltimaAlteracao(double saldo, String nickDoAutor) throws RemoteException {
        this.mw.setLabelSaldo(saldo);
        this.mw.atualizarUltimaMovimentacao(nickDoAutor);
    }

    @Override
    public synchronized void atualizarUsuariosConectados(List<ClientAccountInt> usuariosConectados) throws RemoteException {
        this.usuariosConectados = usuariosConectados;
        this.mw.setLabelQuantidadeClientes(usuariosConectados.size());
    }

    public void callDisconnect() throws RemoteException {
        sa.disconnect(this);
    }

    public void callDeposit(double amount) throws RemoteException {
        sa.deposit(nickname, amount);
    }

    public void callWithdraw(double amount) throws RemoteException {
        sa.withdraw(nickname, amount);
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ServerAccountInt getSa() {
        return sa;
    }

    public void setSa(ServerAccountInt sa) {
        this.sa = sa;
    }

    public List<ClientAccountInt> getUsuariosConectados() {
        return usuariosConectados;
    }


    @Override
    public String toString() {
        return "ClientAccount{" + "nickname=" + nickname + ", sa=" + sa + '}';
    }

}
