package client;

import SharedInfo.RPCMetaData;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientController {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("params: <hostname> <server-port>");
            System.exit(1);
        }

        String hostname = args[0];
        int serverPort  = Integer.parseInt(args[1]); // Porta do socket do processo alvo
        Socket sc = null;

        try {

            // Conectar o socket à porta remota
            sc = new Socket(hostname, serverPort);
            System.out.printf("Socket cliente criado com endereço '%s' conectado com socket '%s'\n", sc.getLocalSocketAddress(), sc.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream( sc.getInputStream() );
            ObjectOutputStream objOutStream = new ObjectOutputStream( sc.getOutputStream() );


            /* Montando o pacote que representará os dados para a RP */
            // definição dos parâmetros da função remota
            ArrayList<Object> RPArgs = new ArrayList<>(2);
            RPArgs.add(0, 2);
            RPArgs.add(1, 123);
            RPArgs.add(2, "Foo");

            // construção do objeto que será recebido pelo servidor
            RPCMetaData RPData = new RPCMetaData(RPCMetaData.ID_RP_SOMAR, RPArgs);

            // [send] envio do objeto que representa os meta-dados da RP
            objOutStream.writeObject(RPData);
            System.out.printf("[send] '%s' call somar(%d, %d)\n", "Foo", 2, 123);

            // [receive] recebimento do resultado retornado da RPC
            int result = in.readInt();
            System.out.println("[receive] " + result);


            // fim do uso
            objOutStream.close();
            in.close();

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
