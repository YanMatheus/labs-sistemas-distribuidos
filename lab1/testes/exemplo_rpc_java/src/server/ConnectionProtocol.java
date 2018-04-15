package server;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.ArrayList;


/**
 *
 */
public class ConnectionProtocol {

  private Socket socket;
  ObjectInputStream in;
  ObjectOutputStream out;

  /**
   * Inicializa uma conexão com um um socket
   * que será tratado como <em>client</em>.
   * @param targetSocket
   */
  public ConnectionProtocol(Socket targetSocket) {
    this.socket = targetSocket;

    try {

      this.out = new ObjectOutputStream( socket.getOutputStream() );
      this.in = new ObjectInputStream( socket.getInputStream() );

    } catch (IOException ex) {
      Logger.getLogger(ConnectionProtocol.class.getName() )
            .log(Level.SEVERE, "Erro de I/O ao abrir stream", ex);
    }
  }

  /**
   * Escreve a confirmação (ou não) se
   * uma função remota foi encontrada no servidor.
   * @param found - Must be <code>true</code> if the remote procedure was found.
   */
  void sendRPCStatus(Boolean found) {
    try {
      this.out.writeBoolean(found);
      this.out.flush();
      InfoLog.printToStdout("sended status 'RPC %s'", found ? "FOUND" : "NOT FOUND");
    } catch (IOException ex) {
      Logger.getLogger( ConnectionProtocol.class.getName() )
            .log(Level.SEVERE, "Erro de I/O ao enviar status da RP", ex);
    }
  }

  /**
   * Lê a estrutura de dados que representa
   * os meta-dados da remote procedure chamada pelo cliente.
   * @return Os meta-dados do procedimento remoto.
   */
  RPCMetaData receiveRPCMetaData() throws IOException, ClassNotFoundException {
    return (RPCMetaData) this.in.readObject();
  }

  /**
   * Fecha o fluxo (stream) de dados do cliente
   * e a conexão com o socket do mesmo.
   */
  void close() {
    try {

      in.close();
      out.close();
      socket.close();

    } catch (IOException ex) {
      Logger.getLogger( ConnectionProtocol.class.getName() )
            .log(Level.SEVERE, "Erro de I/O ao fechar conexão", ex);
    }
  }

}
