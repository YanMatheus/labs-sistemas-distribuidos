package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import shared.ChatServerInt;

public class ClientMain {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("params: <server_ip> <server_port> <user_name>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String name = args[2];

        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ChatServerInt server = (ChatServerInt) registry.lookup(ChatServerInt.SERVICE_NAME);
            new Thread(new ChatClient(server, name)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
