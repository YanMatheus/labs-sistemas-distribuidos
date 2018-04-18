package server;

import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author Micael
 */
public class ConnectionProtocol implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Inicializa uma conexão com um um socket que será tratado como
     * <em>client</em>.
     *
     * @param targetSocket
     * @param mapRP
     * @param rootDir
     */
    public ConnectionProtocol(Socket targetSocket) {
        this.socket = targetSocket;

        try {

            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

            try {
                (new Thread(this)).start(); // chama o método `run`
            } catch (IllegalThreadStateException ex) {
                Logger.getLogger(ConnectionProtocol.class.getName())
                        .log(Level.SEVERE, "thread was already started", ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(ConnectionProtocol.class.getName())
                    .log(Level.SEVERE, "Erro de I/O ao abrir stream", ex);
        }
    }

    /**
     *
     */
    @Override
    public void run() {
    }


    /**
     * Fecha o fluxo (stream) de dados do cliente e a conexão com o socket do
     * mesmo.
     */
    void close() {
        try {

            in.close();
            out.close();
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ConnectionProtocol.class.getName())
                    .log(Level.SEVERE, "Erro de I/O ao fechar conexão", ex);
        }
    }

}
