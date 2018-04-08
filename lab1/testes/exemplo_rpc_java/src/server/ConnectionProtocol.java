package server;

import java.io.*;
import java.net.*;
import java.util.logging.*;

@FunctionalInterface
interface RunnableRemoteProcedure {
  void run(DataInputStream in, DataOutputStream out) throws EOFException, IOException;
}

/**
 *
 */
public class ConnectionProtocol {

  private Socket socket;
  DataInputStream in = null;
  DataOutputStream out = null;

  /**
   * Realiza as ações para o
   * procedimento remoto `Somar`.
   * @throws EOFException - if this input stream reaches the end before reading four bytes.
   * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
   */
  static void doSomar(DataInputStream in, DataOutputStream out) throws EOFException, IOException {
    // [receive] ler os parâmetros de entrada para o procedimento `somar`
    int arg1 = in.readInt();
    int arg2 = in.readInt();
    InfoLog.printToStdout("client called RPC somar(%d, %d)", arg1, arg2);

    // realizar a operação do procedimento `somar`
    int result = arg1 + arg2;

    // [send] escrever o resultado do procedimento `somar`
    out.writeInt(result);
    InfoLog.printToStdout("sended int '%d' to client", result);
  }

  /**
   * Realiza as ações para o
   * procedimento remoto `Multiplicar`.
   * @throws EOFException - if this input stream reaches the end before reading four bytes.
   * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
   */
  static void doMultiplicar(DataInputStream in, DataOutputStream out) throws EOFException, IOException {
    // [receive] ler os parâmetros de entrada para o procedimento `multiplicar`
    int arg1 = in.readInt();
    int arg2 = in.readInt();
    InfoLog.printToStdout("client called RPC multiplicar(%d, %d)", arg1, arg2);

    // realizar a operação do procedimento `multiplicar`
    int result = arg1 * arg2;

    // [send] escrever o resultado do procedimento `multiplicar`
    out.writeInt(result);
    InfoLog.printToStdout("sended int '%d' to client", result);
  }


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
   * Tenta recuperar o identificador
   * do procedimento remoto.
   * @return O identificador encontrado.
   * @throws EOFException - if this input stream reaches the end before reading four bytes.
   * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
   */
  short getRemoteProcedureIdentifier() throws EOFException, IOException {
    return this.in.readShort();
  }

  /**
   * Escreve a confirmação (ou não) se
   * uma função remota foi encontrada no servidor.
   * @param found - Must be <code>true</code> if the remote procedure was found.
   */
  void sendRPCStatus(Boolean found) {
    try {
      this.out.writeBoolean(found);
    } catch (IOException ex) {
      Logger.getLogger( ConnectionProtocol.class.getName() )
            .log(Level.SEVERE, "Erro de I/O ao enviar status da RP", ex);
    }
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
