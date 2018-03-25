package TCP;

import TCP.Documento;
import java.net.*;
import java.io.*;

public class TCPServer {

  public static void main(String[] args) throws ClassNotFoundException, IOException {
    if (args.length != 1) {
      System.err.println("params: <server-port>");
      System.exit(1);
    }

    int porta = Integer.parseInt(args[0]);
    ServerSocket soqueteServidor = null;

    try {

      // Criar socket de escuta e liga-o à porta definida
      soqueteServidor = new ServerSocket(porta);
      System.out.printf("*** Servidor escutando na porta %d...\n", soqueteServidor.getLocalPort());

      while (true) {
        System.out.println("------------------");

        // `accept` bloqueia o servidor até que chegue um pedido de conexão de um cliente
        Socket sc = soqueteServidor.accept(); // Aceitar uma conexão e criar novo socket para atendê-la

        // Criar fluxos de comunicação de entrada e saída com o socket conectado
        DataInputStream dataInStreamClient = new DataInputStream( sc.getInputStream() );
        DataOutputStream dataOutStreamClient = new DataOutputStream( sc.getOutputStream() );
        // dataOutStreamClient.flush();

        String clientId = dataInStreamClient.readUTF();
        dataOutStreamClient.writeUTF("[msg do servidor] Olá client " + clientId);

        System.out.printf("*** Conexão remota aceita de '%s' [%s] \n", sc.getRemoteSocketAddress(), clientId);

        ObjectInputStream objInputStream = new ObjectInputStream( sc.getInputStream() );
        while (true) { // Tentar desempacotar todos os pacotes recebidos do cliente
          try {
            Documento docCliente = (Documento) objInputStream.readObject();
            System.out.printf("*** recebido de %s: '%s'\n", clientId, docCliente);
          } catch (IOException e) { break; }
        }

        try {
          dataInStreamClient.close();
          dataOutStreamClient.close();
          sc.close();
          System.out.printf("*** A conexão com '%s' foi fechada\n", clientId);
        } catch (IOException e) {
          System.err.println("[IO - erro ao fechar client socket] " + e.getMessage());
        }

      }

    } finally {
      if (soqueteServidor != null)
        try {
          soqueteServidor.close();
        } catch (IOException e) {
          System.err.println("[IO - erro ao fechar server socket] " + e.getMessage());
        }
    }

  }

}
