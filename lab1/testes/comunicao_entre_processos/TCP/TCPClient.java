package TCP;

import TCP.Documento;
import java.net.*;
import java.io.*;

public class TCPClient {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("params: <hostname> <server-port> <client-id>");
      System.exit(1);
    }

    String hostname = args[0];
    int serverPort  = Integer.parseInt(args[1]); // Porta do socket do processo alvo
    String clientId = args[2];
    Socket sc = null;

    try {

      // Conectar o socket à porta remota
      sc = new Socket(hostname, serverPort);
      System.out.printf("Socket cliente criado com endereço '%s' conectado com socket '%s'\n", sc.getLocalSocketAddress(), sc.getRemoteSocketAddress());

      // Criar fluxos de comunicação de entrada e saída
      DataOutputStream dataOutStream = new DataOutputStream( sc.getOutputStream() );
      DataInputStream dataInStream = new DataInputStream( sc.getInputStream() );
      // dataOutStream.flush();

      dataOutStream.writeUTF(clientId); // envia

      String streamRecebida = dataInStream.readUTF(); // bloqueia até receber
      System.out.println(streamRecebida);

      ObjectOutputStream objOutStream = new ObjectOutputStream( sc.getOutputStream() );

      Documento d1 = new Documento("Divina Comedia", "Dante"); // objeto a ser transferido
      objOutStream.writeObject(d1);

      Documento d2 = new Documento("Dom Casmurro", "M. de Assis"); // outro objeto a ser transferido
      objOutStream.writeObject(d2);

      dataInStream.close();
      dataOutStream.close();
      objOutStream.close();

    } catch (UnknownHostException | SecurityException e) {
      System.err.println("[InetAddress] " + e.getMessage());
    } catch (EOFException e) {
      System.err.println("[IO - fim da transferência] " + e.getMessage());
    } catch (IOException e) {
      System.err.println("[IO] " + e.getMessage());
    } finally {
      if (sc != null)
        try {
          sc.close();
        } catch (IOException e) {
          System.err.println("[IO - erro ao fechar socket] " + e.getMessage());
        }
    }
  }

}
