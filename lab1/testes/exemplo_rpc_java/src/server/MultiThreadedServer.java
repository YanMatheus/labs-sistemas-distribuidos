package server;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.Map;
import java.util.HashMap;

/**
 *
 */
public class MultiThreadedServer {

  /**
   * Define todas os procedimentos remotos
   * que estarão disponíveis para o processo cliente.
   *
   * @param m -
   */
  public static void setRemoteProcedures(Map<Short, RunnableRemoteProcedure> m) {
    m.put(      RPCMetaData.ID_RP_SOMAR, RemoteProcedure::doSomar);
    m.put(RPCMetaData.ID_RP_MULTIPLICAR, RemoteProcedure::doMultiplicar);
  }


  public static void main(String[] args) {
    int ssPort = (args.length == 1)
                 ? Integer.parseInt(args[0])
                 : 4444;

    Map<Short, RunnableRemoteProcedure> mapRP = new HashMap<>();
    setRemoteProcedures(mapRP);

    long clientsAmount = 0;

    // Conectar o processo a um socket
    ServerSocket ss = null;

    try {
      ss = new ServerSocket(ssPort);
    } catch (IOException ex) {
      Logger.getLogger(MultiThreadedServer.class.getName())
            .log(Level.SEVERE, "Erro socket bind", ex);
      System.exit(1);
    }

    InfoLog.printToStdout("server running at port %d", ssPort);

    while (true) {
      Socket cs = null;

      try {
        cs = ss.accept();
        clientsAmount++;
      } catch (IOException ex) { break; }
      InfoLog.printToStdout("{%d} connection establish with '%s'",
                           clientsAmount, cs.getRemoteSocketAddress());

      // Inicia uma nova thread para tratar esse client (thread per connection)
      new ConnectionProtocol(cs, mapRP);
    }
  }

}
