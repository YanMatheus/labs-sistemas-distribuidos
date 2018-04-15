package server;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

@FunctionalInterface
interface RunnableRemoteProcedure {
  void run(RPCMetaData rmd, ObjectOutputStream out) throws EOFException, IOException;
}

/**
 * Reune a implementação de todos
 * os procedimentos remotos disponíveis
 * pelo servidor.
 */
interface RemoteProcedure {

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

}