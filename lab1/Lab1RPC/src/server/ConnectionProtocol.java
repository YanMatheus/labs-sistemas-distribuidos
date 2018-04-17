package server;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.logging.*;
import shared.RPCMetaData;

/**
 *
 * @author micael
 */
public class ConnectionProtocol implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Map<Short, RunnableRemoteProcedure> mapRP;
    private File rootDir;

    /**
     * Inicializa uma conexão com um um socket que será tratado como
     * <em>client</em>.
     *
     * @param targetSocket
     */
    public ConnectionProtocol(Socket targetSocket, Map<Short, RunnableRemoteProcedure> mapRP, File rootDir) {
        this.socket = targetSocket;
        this.mapRP = mapRP;

        try {

            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.rootDir = rootDir;

            try {
                (new Thread(this)).start(); // chama o método `run`
                sendRemoteDir();
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
        do {

            RPCMetaData rmd = null;
            short procedureId = -1;

            try {
                rmd = receiveRPCMetaData();
                procedureId = rmd.getId();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionProtocol.class.getName())
                        .log(Level.SEVERE, "Class of a serialized object cannot be found", ex);
            } catch (IOException ex) {
                break;
            } // cliente fechou a conexão

            RunnableRemoteProcedure remoteProcedure = mapRP.get(procedureId);
            if (remoteProcedure != null) {
                this.sendRPCStatus(true);
                try {
                    remoteProcedure.run(rmd, out, this.rootDir);
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionProtocol.class.getName())
                            .log(Level.WARNING, "Erro ao executar método remoto", ex);
                }
            } else {
                this.sendRPCStatus(false);
                Logger.getLogger(ConnectionProtocol.class.getName())
                        .log(Level.WARNING, "RP not found");
            }

        } while (true);

        this.close();
    }

    /**
     *
     */
    void sendRemoteDir() {
        try {
            this.out.writeObject(rootDir);
            this.out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProtocol.class.getName())
                    .log(Level.SEVERE, "Erro de I/O ao enviar rootDir", ex);
        }
    }

    /**
     * Escreve a confirmação (ou não) se uma função remota foi encontrada no
     * servidor.
     *
     * @param found - Must be <code>true</code> if the remote procedure was
     * found.
     */
    void sendRPCStatus(Boolean found) {
        try {
            this.out.writeBoolean(found);
            this.out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProtocol.class.getName())
                    .log(Level.SEVERE, "Erro de I/O ao enviar status da RP", ex);
        }
    }

    /**
     * Lê a estrutura de dados que representa os meta-dados da remote procedure
     * chamada pelo cliente.
     *
     * @return Os meta-dados do procedimento remoto.
     */
    RPCMetaData receiveRPCMetaData() throws IOException, ClassNotFoundException {
        return (RPCMetaData) this.in.readObject();
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
