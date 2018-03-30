package server;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ConnectionProtocol {

  private Socket socket;
  private DataInputStream in = null;
  private DataOutputStream out = null;

  /**
   * Inicializa uma conexão com um um socket
   * que será tratado como <em>client</em>.
   * @param targetSocket
   */
  public ConnectionProtocol(Socket targetSocket) {
    this.socket = targetSocket;
    try {

      this.in = new DataInputStream( socket.getInputStream() );
      this.out = new DataOutputStream( socket.getOutputStream() );

    } catch (IOException ex) {
      Logger.getLogger(ConnectionProtocol.class.getName() )
            .log(Level.SEVERE, "Erro de I/O ao abrir stream", ex);
    }
  }

  /**
   * Realiza a leitura de dois inteiros
   * no fluxo de dados e soma eles.
   * @return A operação de soma entre dois inteiros.
   * @throws EOFException
   * @throws IOException
   */
  private int doSomar() throws EOFException, IOException {
    if (in == null) return 0;

    int arg1 = in.readInt();
    int arg2 = in.readInt();
    InfoLog.printToStdout("client called soma(%d, %d)", arg1, arg2);
    return arg1 + arg2;
  }

  /**
   * Realiza a soma e envia para o <em>client</em>
   * que iniciou a conexão.
   * @throws EOFException
   * @throws IOException
   */
  void sendSoma() throws EOFException, IOException {
    if (out == null) return;

    int result = doSomar();
    out.writeInt(result);
    InfoLog.printToStdout("sended int '%d' to client", result);
  }

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
