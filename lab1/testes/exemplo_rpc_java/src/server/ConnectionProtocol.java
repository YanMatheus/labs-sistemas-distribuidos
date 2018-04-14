package server;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.ArrayList;

@FunctionalInterface
interface RunnableRemoteProcedure {
  void run(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException;
}

/**
 *
 */
public class ConnectionProtocol {

  private Socket socket;
  ObjectInputStream in;
  ObjectOutputStream out;

  /**
   * Realiza as ações para o
   * procedimento remoto `Somar`.
   * @throws EOFException - if this input stream reaches the end before reading four bytes.
   * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
   */
  static void doSomar(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException {
    ArrayList<Object> args = rmd.getArgs();

    int arg1 = (int) args.get(0);
    int arg2 = (int) args.get(1);
    InfoLog.printToStdout("client called RPC somar(%d, %d)", arg1, arg2);

    // realizar a operação do procedimento `somar`
    int result = arg1 + arg2;

    // [send] escrever o resultado do procedimento `somar`
    out.writeInt(result);
    out.flush();
    InfoLog.printToStdout("sended %s '%d' to client", "int", result);
  }

  /**
   * Realiza as ações para o
   * procedimento remoto `Multiplicar`.
   * @throws EOFException - if this input stream reaches the end before reading four bytes.
   * @throws IOException - the stream has been closed and the contained input stream does not support reading after close, or another I/O error occurs.
   */
  static void doMultiplicar(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException {
    ArrayList<Object> args = rmd.getArgs();

    int arg1 = (int) args.get(0);
    int arg2 = (int) args.get(1);
    InfoLog.printToStdout("client called RPC multiplicar(%d, %d)", arg1, arg2);

    // realizar a operação do procedimento `multiplicar`
    int result = arg1 * arg2;

    // [send] escrever o resultado do procedimento `multiplicar`
    out.writeInt(result);
    out.flush();
    InfoLog.printToStdout("sended %s '%d' to client", "int", result);
  }


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
