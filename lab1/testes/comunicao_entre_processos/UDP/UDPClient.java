package UDP;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class UDPClient {

  public static void main(String[] args) {
    if (args.length < 3) {
      System.err.println("params: <server-port> <hostname> '<mensagem-até-20-bytes>' [socket-port]");
      System.exit(1);
    }


    int serverPort  = Integer.parseInt(args[0]); // porta do servidor
    String hostname = args[1];
    String mensagem = args[2];
    DatagramSocket soqueteA = null;

    try {

      // Criar um socket para o processo cliente ligando-o
      soqueteA = (args.length > 3)
                 ? new DatagramSocket( Integer.parseInt(args[3]) ) // à porta passada, habilitando o broadcast por UDP
                 : new DatagramSocket(); // à uma porta disponível

      System.out.println("Socket criado com endereço " + soqueteA.getLocalSocketAddress());

      byte[] msgBytes = mensagem.getBytes();
      InetAddress hostA = InetAddress.getByName(hostname); // Determinar o IP do hostname

      // Criar o datagrama para envio
      DatagramPacket request = new DatagramPacket(msgBytes, mensagem.length(), hostA, serverPort);

      soqueteA.send(request); // Enviar a mensagem para o servidor
      System.out.println(">> Enviou : '" + mensagem + "'");

      // Preparar o cliente para receber a resposta do servidor
      byte[] buffer = new byte[20];
      DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

      // Receber a resposta (espera abaixo até receber)
      soqueteA.receive(reply);
      String receivedMsg = new String( reply.getData(), StandardCharsets.UTF_8 );
      System.out.println("<< Recebeu: '" + receivedMsg + "'");

    } catch (SocketException e) {
      System.err.println("[Socket] " + e.getMessage());
    } catch (UnknownHostException | SecurityException e) {
      System.err.println("[InetAddress] " + e.getMessage());
    } catch (IOException e) {
      System.err.println("[IO] " + e.getMessage());
    } finally {
      if (soqueteA != null) soqueteA.close();
    }
  }

}
