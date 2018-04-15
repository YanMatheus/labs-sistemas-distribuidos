package server;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.function.Consumer;
import java.util.HashMap;

/**
 *
 */
public class ServerSocketController {

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


  public static void main(String[] args) throws IOException {
    int ssPort = (args.length == 1)
                 ? Integer.parseInt(args[0])
                 : 4444;

    Map<Short, RunnableRemoteProcedure> mapRP = new HashMap<>();
    setRemoteProcedures(mapRP);

    // Conectar o processo a um socket
    ServerSocket ss = new ServerSocket(ssPort);
    InfoLog.printToStdout("server running at port %d", ssPort);

    while (true) {
      Socket cs = ss.accept();
      InfoLog.printToStdout("connection establish with '%s'",
                            cs.getRemoteSocketAddress());

      // Inicia uma nova thread para tratar esse client (thread per connection)
      ConnectionProtocol cp = new ConnectionProtocol(cs);
      // >>>>>>>>>>
      do {

        RPCMetaData rmd = null;
        short procedureId = -1;

        try {
          rmd = cp.receiveRPCMetaData();
          procedureId = rmd.getId();
        } catch (ClassNotFoundException ex) {
          InfoLog.printToStderr("[error:%s] Class of a serialized object cannot be found", "main");
        } catch (IOException ex) { break; } // cliente fechou a conexão

        RunnableRemoteProcedure remoteProcedure = mapRP.get(procedureId);
        if (remoteProcedure != null) {
          cp.sendRPCStatus(true);
          remoteProcedure.run(rmd, cp.getOutputStream());
        } else {
          cp.sendRPCStatus(false);
          InfoLog.printToStderr("[error:%s] RP not found", "main");
        }

      } while (true);
      // <<<<<<<<<<

      cp.close();
    }
  }

}
