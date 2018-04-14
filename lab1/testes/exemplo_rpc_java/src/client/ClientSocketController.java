package client;

import shared.RPCMetaData;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.ArrayList;


/**
 * Representar uma conexão com um client.
 * Usando sockets stream para trocar mensagens
 * entre dois hosts distintos.
 */
public class ClientSocketController {

  Socket cs;
  ObjectInputStream in;
  ObjectOutputStream out;

  /**
   * Realiza a "chamada" da
   * Remote Procedure <code>Somar</code>.
   * A assinatura deve ser a mesma que a da RP.
   * @param num1 - Primeiro argumento da RP
   * @param num2 - Segundo argumento da RP
   * @return A soma entre os dois números passados.
   */
  public int callRPCSomar(int num1, int num2) throws IOException {
    // definição dos argumentos para a função remota `Somar`
    ArrayList<Object> RPArgs = new ArrayList<>(2);
    RPArgs.add(0, num1);
    RPArgs.add(1, num2);

    // construção do objeto que será recebido pelo servidor
    RPCMetaData RPData = new RPCMetaData(RPCMetaData.ID_RP_SOMAR, RPArgs);

    // [send] envio do objeto que representa os meta-dados da RP
    this.out.writeObject(RPData);
    System.out.printf("[send] called somar(%d, %d)\n", RPArgs.get(0), RPArgs.get(1));

    // [receive] ler a confirmação do server sobre se o procedimento remoto foi encontrado
    Boolean rpcFound = this.in.readBoolean();
    System.out.printf("[receive] RPC status '%s'\n", rpcFound ? "FOUND" : "NOT FOUND");

    if (rpcFound) {
      System.out.printf("[waiting] server RPC response\n");
      // [receive] ler o resultado obtido da chamada do procedimento
      int result = this.in.readInt(); // Bloqueia até receber a resposta do server
      System.out.printf("[receive] RPC result: %d\n", result);
      return result;
    }

    // TODO: tratar erro de `RP not found`
    return 0;
  }

  /**
   * Realiza a "chamada" da
   * Remote Procedure <code>Multiplicar</code>.
   * A assinatura deve ser a mesma que a da RP.
   * @param num1 - Primeiro argumento da RP
   * @param num2 - Segundo argumento da RP
   * @return A multiplicação entre os dois números passados.
   */
  public int callRPCMultiplicar(int num1, int num2) throws IOException {
    // definição dos argumentos para a função remota `Somar`
    ArrayList<Object> RPArgs = new ArrayList<>(2);
    RPArgs.add(0, num1);
    RPArgs.add(1, num2);

    // construção do objeto que será recebido pelo servidor
    RPCMetaData RPData = new RPCMetaData(RPCMetaData.ID_RP_MULTIPLICAR, RPArgs);

    // [send] envio do objeto que representa os meta-dados da RP
    this.out.writeObject(RPData);
    System.out.printf("[send] client called multiplicar(%d, %d)\n", RPArgs.get(0), RPArgs.get(1));

    // [receive] ler a confirmação do server sobre se o procedimento remoto foi encontrado
    Boolean rpcFound = this.in.readBoolean();
    System.out.printf("[receive] RPC status '%s'\n", rpcFound ? "FOUND" : "NOT FOUND");

    if (rpcFound) {
      System.out.printf("[waiting] server RPC response\n");
      // [receive] ler o resultado obtido da chamada do procedimento
      int result = this.in.readInt(); // Bloqueia até receber a resposta do server
      System.out.printf("[receive] RPC result: %d\n", result);
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
      System.out.printf("Socket cliente criado com endereço '%s' conectado com socket '%s'\n",
                        cs.getLocalSocketAddress(), cs.getRemoteSocketAddress());

      in = new ObjectInputStream( cs.getInputStream() );
      out = new ObjectOutputStream( cs.getOutputStream() );

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
