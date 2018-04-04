package client;

import java.io.*;
import java.net.*;
import java.util.logging.*;


/**
 * Representar uma conexão com um client.
 * Usando sockets stream para trocar mensagens
 * entre dois hosts distintos.
 */
public class ClientSocketController {

  Socket cs;
  DataInputStream in;
  DataOutputStream out;

  /**
   *
   * @param num1 -
   * @param num2 -
   * @return
   */
  public int callRPCSomar(int num1, int num2) throws IOException {
    this.out.writeShort(1);
    // [receive] ler a confirmação do server sobre se o procedimento remoto foi encontrado
    Boolean rpcFound = this.in.readBoolean();

    if (rpcFound) {
      // [send] escrever os parâmetros de entrada que o procedimento espera
      this.out.writeInt(num1);
      this.out.writeInt(num2);

      // [receive] ler o resultado obtido da chamada do procedimento
      int result = this.in.readInt(); // Bloqueia até receber a resposta do server
      return result;
    }

    // TODO: tratar erro de `RP not found`
    return 0;
  }

  /**
   *
   * @param num1 -
   * @param num2 -
   * @return
   */
  public int callRPCMultiplicar(int num1, int num2) throws IOException {
    this.out.writeShort(2);
    // [receive] ler a confirmação do server sobre se o procedimento remoto foi encontrado
    Boolean rpcFound = this.in.readBoolean();

    if (rpcFound) {
      // [send] escrever os parâmetros de entrada que o procedimento espera
      this.out.writeInt(num1);
      this.out.writeInt(num2);

      // [receive] ler o resultado obtido da chamada do procedimento
      int result = this.in.readInt(); // Bloqueia até receber a resposta do server
      return result;
    }

    // TODO: tratar erro de `RP not found`
    return 0;
  }


  /**
   *
   * @param serverHost - the server host name, or null for the loopback address.
   * @param serverPort - the port number which server host is listening.
   * @throws IOExcepetion
   */
  public ClientSocketController(String serverHost, int serverPort) throws IOException {
    try {

      // Se conectar com um processo servidor
      cs = new Socket(serverHost, serverPort);
      in = new DataInputStream( cs.getInputStream() );
      out = new DataOutputStream( cs.getOutputStream() );

    } catch (UnknownHostException ex) {
      Logger.getLogger(ClientSocketController.class.getName())
            .log(Level.SEVERE, "Endereço de IP do host não foi determinado", ex);
      System.exit(2);
    } catch (SecurityException ex) {
      Logger.getLogger(ClientSocketController.class.getName())
            .log(Level.SEVERE, "Erro de segurança", ex);
      System.exit(3);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(ClientSocketController.class.getName())
            .log(Level.SEVERE, "Porta fora do range válido (entre 0 e 65.535)", ex);
      System.exit(4);
    }
  }

  /**
   * Tenta fechar o socket criado
   * assim como os fluxos de dados
   * de entrada e saída.
   */
  public void closeSocket() {
    try {

      this.in.close();
      this.out.close();
      this.cs.close();

    } catch (IOException ex) {
      Logger.getLogger(ClientSocketController.class.getName())
            .log(Level.SEVERE, "Erro de I/O ao fechar client socket", ex);
    }
  }

}
