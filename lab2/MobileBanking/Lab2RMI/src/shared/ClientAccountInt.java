package shared;

/**
 *
 * @author Micael
 */
public interface ClientAccountInt extends java.rmi.Remote {
    public void atualizarUltimaAlteracao(double saldo, String nickDoAutor) throws java.rmi.RemoteException;
    public void atualizarUsuariosConectados(java.util.List<ClientAccountInt> clients) throws java.rmi.RemoteException;
    public String getNickname() throws java.rmi.RemoteException;
}
