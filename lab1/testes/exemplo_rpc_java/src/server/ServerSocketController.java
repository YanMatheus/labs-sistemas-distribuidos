package server;

import java.io.*;
import java.net.*;

public class ServerSocketController {

  public static void main(String[] args) throws IOException {
    int ssPort = (args.length == 1)
                 ? Integer.parseInt(args[0])
                 : 4444;

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
        try {
          cp.sendSoma();
        } catch (IOException ex) { break; }
      } while (true);

      cp.close();
    }
  }

}
