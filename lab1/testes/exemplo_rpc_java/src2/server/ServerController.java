package server;

import SharedInfo.RPCMetaData;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerController {

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

                System.out.printf("*** Conexão remota aceita de '%s'\n", sc.getRemoteSocketAddress());

                DataOutputStream dataOutStreamClient = new DataOutputStream( sc.getOutputStream() );
                ObjectInputStream objInputStream = new ObjectInputStream( sc.getInputStream() );

                try {

                    RPCMetaData RPData = (RPCMetaData) objInputStream.readObject();
                    System.out.printf("[read] id=%h\n", RPData.getId());

                    // RP realiza a operação para a func com id passado
                    ArrayList<Object> RPParams = RPData.getArgs();
                    int arg1 = (int) RPParams.get(0);
                    int arg2 = (int) RPParams.get(1);
                    int result = arg1 + arg2;
                    String id = (String) RPParams.get(2);

                    dataOutStreamClient.writeInt(result);
                    System.out.printf("[send] somar(%d, %d) para %s\n", arg1, arg2, id);

                } catch (IOException e) { break; }

                try {
                    objInputStream.close();
                    sc.close();
                    System.out.printf("*** A conexão foi fechada\n");
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
