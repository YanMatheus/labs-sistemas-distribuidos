package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import shared.ClientAccountInt;
import shared.ServerAccountInt;


/**
 *
 * @author Micael
 */
public class Account implements ServerAccountInt {
    private static final long serialVersionUID = 1L;

    private List<ClientAccountInt> clients;
    private double balance;
    private String nicknameLastClientChangeBalance;

    public Account() throws RemoteException {
        clients = new ArrayList<>();
        balance = 0d;
        nicknameLastClientChangeBalance = null;
    }


    @Override
    public synchronized void connect(ClientAccountInt ca) throws RemoteException {
        clients.add(ca);
        if (nicknameLastClientChangeBalance != null)
            ca.atualizarUltimaAlteracao(balance, nicknameLastClientChangeBalance);

        broadcastClientList();
        InfoLog.printToStdout("cliente com nick '%s' entrou [%d]", ca.getNickname(), clients.size());
    }

    @Override
    public synchronized void disconnect(ClientAccountInt ca) throws RemoteException {
        clients.remove(ca);
        broadcastClientList();
        InfoLog.printToStdout("o cliente '%s' saiu", ca.getNickname());
    }

    @Override
    public synchronized void broadcastBalance() throws RemoteException {
        for (ClientAccountInt client : clients)
            client.atualizarUltimaAlteracao(balance, nicknameLastClientChangeBalance);
    }

    @Override
    public synchronized void broadcastClientList() throws RemoteException {
        for (ClientAccountInt client : clients)
            client.atualizarUsuariosConectados(clients);
    }

    @Override
    public synchronized double showBalance() throws RemoteException {
        return balance;
    }

    @Override
    public synchronized void deposit(String nickname, double amount) throws RemoteException {
        balance += amount;
        InfoLog.printToStdout("cliente '%s' depositou %.2f", nickname, amount);
        nicknameLastClientChangeBalance = nickname;
        broadcastBalance();
    }

    @Override
    public synchronized void withdraw(String nickname, double amount) throws RemoteException {
        balance -= amount;
        InfoLog.printToStdout("cliente '%s' retirou %.2f", nickname, amount);
        nicknameLastClientChangeBalance = nickname;
        broadcastBalance();
    }

}
