package UDP.multicast;

import java.net.*;
import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Envia uma menssagem para
 * um grupo multicast que
 * não precisa existir.
 */
public class Emissor {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("params: <group-port> <username> <20-bytes-message>");
      System.exit(1);
    }

    final DateFormat DATE_FORMAT = new SimpleDateFormat("[HH:mm:ss]");
    int porta = Integer.parseInt(args[0]);
    String user = args[1];
    String msg = user + ": " + args[2];
    String enderecoGrupo = "230.1.2.3"; // de 224.0.0.0 a 239.255.255.255
    DatagramSocket s = null;

    try {
      s = new DatagramSocket();
    } catch (SocketException e) {
      System.err.println(e.getMessage());
    }

    try {
      InetAddress ipGrupo = InetAddress.getByName(enderecoGrupo);

      System.out.printf("1) Datagrama soquete criado com endereço ~ %s\n", s.getLocalSocketAddress());
      System.out.printf("2) Grupo alvo ~ %s:%d\n", ipGrupo.getHostAddress(), porta);

      byte[] dados = msg.getBytes();
      DatagramPacket pacote = new DatagramPacket(dados, dados.length, ipGrupo, porta);

      try {
        s.send(pacote);
        Date time = new Date();
        System.out.printf("3) %s Mensagem enviada ~ '%s'\n",  DATE_FORMAT.format(time), msg);
      } catch (IOException e) {
        System.err.println("[IO - mensagem não enviada] " + e.getMessage());
      }

      s.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
