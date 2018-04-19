package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInt extends Remote {
    static final String SERVICE_NAME = "ChatServer";

    void connect(String name, ChatClientInt c) throws RemoteException;
    void disconnect(ChatClientInt c) throws RemoteException;
    void broadcast(String name, String s) throws RemoteException;
    void list(ChatClientInt c) throws RemoteException;
    ChatClientInt lookup(String name) throws RemoteException;
}