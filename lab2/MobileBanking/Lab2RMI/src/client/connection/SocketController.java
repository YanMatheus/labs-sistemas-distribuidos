package client.connection;

import java.io.IOException;

/**
 * Representa uma conexão do client com o server.
 * Usa-se sockets stream para a troca de mensagens
 * entre dois hosts remotos.
 *
 * @author Micael
 * @author Victor
 */
public class SocketController {

    /**
     *
     * @param serverHost - the server host name, or <code>null</code> for the loopback address.
     * @param serverPort - the port number which server host is listening.
     * @throws IOException
     */
    public SocketController(String serverHost, int serverPort) throws IOException, ClassNotFoundException {
    }

}
