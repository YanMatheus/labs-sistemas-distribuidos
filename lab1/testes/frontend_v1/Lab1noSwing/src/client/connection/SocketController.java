package client.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa uma conexão do client com o server.
 * Usa-se sockets stream para a troca de mensagens
 * entre dois hosts remotos.
 *
 * @author micael
 */
public class SocketController {

    private Socket cs;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    /**
     *
     * @param serverHost - the server host name, or <code>null</code> for the loopback address.
     * @param serverPort - the port number which server host is listening.
     * @throws IOException
     */
    public SocketController(String serverHost, int serverPort) throws IOException {
        try {

            // Conectar com um processo remoto
            cs = new Socket(serverHost, serverPort);
            System.out.printf("Socket cliente criado com endereço '%s' conectado com socket '%s'\n",
                cs.getLocalSocketAddress(), cs.getRemoteSocketAddress());

            // Abrir um canal para a troca de mensagens
            in = new ObjectInputStream( cs.getInputStream() );
            out = new ObjectOutputStream( cs.getOutputStream() );

        } catch (UnknownHostException ex) {
            Logger.getLogger(SocketController.class.getName())
                  .log(Level.SEVERE, "Endereço de IP do host não foi determinado", ex);
            System.exit(2);
        } catch (SecurityException ex) {
            Logger.getLogger(SocketController.class.getName())
                  .log(Level.SEVERE, "Erro de segurança", ex);
            System.exit(3);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SocketController.class.getName())
                  .log(Level.SEVERE, "Porta fora do range válido (entre 0 e 65.535)", ex);
            System.exit(4);
        }
    }

    /**
     * Tenta fechar o socket criado
     * assim como os fluxos de dados
     * de entrada e saída.
     */
    public void closeSocket() {
        try {

            this.in.close();
            this.out.close();
            this.cs.close();

        } catch (IOException ex) {
            Logger.getLogger(SocketController.class.getName())
                  .log(Level.SEVERE, "Erro de I/O ao fechar client socket", ex);
        }
    }

}
