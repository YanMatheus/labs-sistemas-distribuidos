package UDP.multicast;

import java.net.*;
import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cria um Socket Multicast e entra em um
 * grupo multicast (criado se não exisitir)
 * com a porta passada.
 */
public class Receptor {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("params: <group-port>");
      System.exit(1);
    }

    final DateFormat DATE_FORMAT = new SimpleDateFormat("[HH:mm:ss]");
    String enderecoGrupo = "230.1.2.3"; // de 224.0.0.0 a 239.255.255.255
    int porta = Integer.parseInt(args[0]);

    InetAddress ipGrupo = null;
    MulticastSocket ms = null;

    // Criar socket e entrar em um grupo
    try {
      ipGrupo = InetAddress.getByName(enderecoGrupo);
      ms = new MulticastSocket(porta);
      System.out.println("1) Socket criado com endereço ~ " + ms.getLocalSocketAddress());

      ms.joinGroup(ipGrupo);
      System.out.println("2) Entrou no grupo ~ " + ipGrupo.getHostAddress());
    } catch (SocketException e) {
      System.err.println("[Socket] " + e.getMessage());
    } catch (UnknownHostException e) {
      System.err.println("[Host - host desconhecido] " + e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Ler continuamente menssagens enviadas para o grupo
    byte[] buffer = new byte[100]; // deve suportar um texto de 20 bytes + um username com tamanho indefinido
    while (true) {
      DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
      System.out.println("3) Esperando mensagens...");

      try {
        ms.setSoTimeout(120_000);
        ms.receive(pacoteRecebido);
      } catch (SocketTimeoutException e) {
        break;
      } catch (IOException e) {
        System.out.println("[IO] " + e.getMessage());
      }

      String str = new String( pacoteRecebido.getData(), 0, buffer.length );
      Date time = new Date();
      System.out.printf("4) %s Mensagem recebida de %s ~ '%s'\n",
        DATE_FORMAT.format(time), pacoteRecebido.getSocketAddress(), str.trim());
    }

   // Sair do grupo e fechar socket
   try {
     ms.leaveGroup(ipGrupo);
     ms.close();
   } catch (IOException e) {
     e.printStackTrace();
   }
  }

}
