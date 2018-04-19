package server;
// (c) https://gouraharid.blogspot.com.br/2009/10/chat-program-using-jdk-6-and-rmi.html

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import shared.ChatServerInt;

public class ServerMain {

    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("param: <server_port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try {
            // System.setOut( new PrintStream("server.log") ); // ativar o log num arquivo
            ChatServerInt server = new ChatServer();
            ChatServerInt stub = (ChatServerInt) UnicastRemoteObject.exportObject(server, port);

            Registry r = LocateRegistry.createRegistry(port);
            r.rebind(ChatServerInt.SERVICE_NAME, stub);


            ChatServer.writeLog("Server started at {0}, waiting for connections...");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}