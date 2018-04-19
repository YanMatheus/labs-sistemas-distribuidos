package server;
// (c) https://gouraharid.blogspot.com.br/2009/10/chat-program-using-jdk-6-and-rmi.html
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shared.*;

public class ChatServer implements ChatServerInt {
    private static final long serialVersionUID = 1L;

    private List myclients;
    private List clientNames;
    private static final String NAME = "server";

    public ChatServer() throws RemoteException {
        // super();
        myclients = new ArrayList();
        clientNames = new ArrayList();
    }


    public synchronized void disconnect(ChatClientInt c) throws RemoteException {
        myclients.remove(c);
        clientNames.remove( c.getName() );
        writeLog(c.getName() + " disconnected at {0}");

        broadcast(NAME, c.getName() + " has left.");
    }

    public synchronized void list(ChatClientInt c) throws RemoteException {
        c.update(NAME, "Active users: " + clientNames.toString());
    }

    public synchronized ChatClientInt lookup(String name) throws RemoteException {
        ChatClientInt c = null;
        int index = clientNames.indexOf(name);
        if (-1 != index) c = (ChatClientInt) myclients.get(index);
        return c;
    }

    public synchronized void connect(String n, ChatClientInt c) throws RemoteException {
        broadcast(NAME, n + " is joining now...");

        clientNames.add(n);
        myclients.add(c);
        int count = myclients.size();

        StringBuffer wcmMsg = new StringBuffer("Welcome ").append(n).append(", ");
        wcmMsg
            .append("There ")
            .append((count == 1) ? "is " : "are ")
            .append(count)
            .append((count == 1) ? " user: " : " users: ");

        wcmMsg.append( clientNames.toString() );

        c.update(NAME, wcmMsg.toString());
        writeLog(n + " connected at {0}");
    }

    public synchronized void broadcast(String name, String msg) throws RemoteException {
        for (Object objc : myclients) {
            ((ChatClientInt) objc).update(name, msg);
        }

        writeLog("{0}: " + name + ": " + msg);
    }


    // extra
    public static void writeLog(String log) {
        System.out.println(
            MessageFormat.format(log, new Date().toString())
        );
    }
}