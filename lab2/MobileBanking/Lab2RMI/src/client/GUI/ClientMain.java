package client.GUI;

import client.connection.ClientAccount;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import shared.AccountState;
import shared.ServerAccountInt;

/**
 * Inicializa a janela principal e
 * cordena a conexão do cliente com o servidor.
 *
 * @author Yan
 * @author Micael
 */
public class ClientMain {

    ClientAccount conta;

    void fecharConexao() throws RemoteException {
        conta.callDisconnect();
    }

    void depositar(double amount) throws RemoteException {
        conta.callDeposit(amount);
    }

    void sacar(double amount) throws RemoteException {
        conta.callWithdraw(amount);
    }

    boolean definirEstadoOcupado() throws RemoteException {
        if ( conta.stateIsBusy() ) return false;
        conta.callSetState(AccountState.BUSY);
        return true;
    }

    boolean definirEstadoDisponivel() throws RemoteException {
        conta.callSetState(AccountState.DONE);
        return true;
    }


    public static void main(String[] args) {

        // estilo
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        ClientMain cc = new ClientMain();
        MainWindow mainWindow = new MainWindow(cc);
        ConnectionDialog connDialog = new ConnectionDialog(mainWindow, true);
        connDialog.setVisible(true);

        do {
            try {
                Registry reg = LocateRegistry.getRegistry(connDialog.ip, connDialog.porta);
                ServerAccountInt server = (ServerAccountInt) reg.lookup(ServerAccountInt.SERVICE_ACCOUNT);
                cc.conta = new ClientAccount(server, mainWindow, connDialog.nickname);
                break;
            } catch (RemoteException | NotBoundException ex) {
                JOptionPane.showMessageDialog(null,
                    "Não foi possível conectar",
                    "Erro ao Conectar :(", JOptionPane.ERROR_MESSAGE);
                connDialog.setVisible(true);
            }
        } while (true);

        connDialog.dispose();
        mainWindow.setLnNickName(connDialog.nickname);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

    }

}
