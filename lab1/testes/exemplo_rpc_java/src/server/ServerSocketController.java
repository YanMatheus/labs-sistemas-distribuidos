package server;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.function.Consumer;
import java.util.HashMap;

/**
 *
 */
public class ServerSocketController {

  public static void main(String[] args) throws IOException {
    int ssPort = (args.length == 1)
                 ? Integer.parseInt(args[0])
                 : 4444;

    Map<Short, RunnableRemoteProcedure> map = new HashMap<>();
    map.put(new Short("1"), ConnectionProtocol::doSomar);
    map.put(new Short("2"), ConnectionProtocol::doMultiplicar);

    // Conectar o processo a um socket
    ServerSocket ss = new ServerSocket(ssPort);
    InfoLog.printToStdout("server running at port %d", ssPort);

    while (true) {
      Socket cs = ss.accept();
      InfoLog.printToStdout("connection establish with '%s'",
      cs.getRemoteSocketAddress());

      // Bloqueia até o client sair
      ConnectionProtocol cp = new ConnectionProtocol(cs);

      do {

        short procedureId;
        try {
          procedureId = cp.getRemoteProcedureIdentifier();
        } catch (IOException ex) { break; } // cliente fechou a conexão

        RunnableRemoteProcedure remoteProcedure = map.get(procedureId);
        if (remoteProcedure != null) {
          cp.sendRPCStatus(true);
          remoteProcedure.run(cp.in, cp.out);
        } else {
          cp.sendRPCStatus(false);
          InfoLog.printToStderr("[error:%s] RP not found", "main");
        }

      } while (true);

      cp.close();
    }
  }

}
