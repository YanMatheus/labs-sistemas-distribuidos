package UDP;

import java.net.*;
import java.io.*;

public class UDPServer {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("params: <server-port>");
      System.exit(1);
    }


    int porta = Integer.parseInt(args[0]);
    DatagramSocket socketB = null;

    try {

      // Criar um socket para o servidor e liga-o à porta definida
      socketB = new DatagramSocket(porta);

      // Criar buffer vazio para a recepção da mensagem
      byte[] buffer = new byte[1000];

      System.out.println("Servidor ouvindo na porta " + porta + "...");
      while(true) {
        System.out.println("------------------");
        // Criar o objeto que receberá a mensagem
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);

        // Bloquear até receber a requisição
        socketB.receive(request);
        System.out.println("< " + request.getSocketAddress());

        // Criar o datagrama para a resposta a ser enviada
        DatagramPacket reply = new DatagramPacket(
          request.getData(), request.getLength(),
          request.getAddress(), request.getPort()
        );

        // Enviar a resposta ao cliente que a requisitou
        System.out.println("> " + reply.getSocketAddress());
        socketB.send(reply);
      }

    } catch (SocketException e) {
      System.err.println("[Socket] " + e.getMessage());
    } catch (IOException e) {
      System.err.println("[IO] " + e.getMessage());
    } finally {
      if (socketB != null) socketB.close();
    }
  }

}
