package client;
// (c) https://gouraharid.blogspot.com.br/2009/10/chat-program-using-jdk-6-and-rmi.html
import java.rmi.RemoteException;
import java.util.Scanner;

import shared.*;

/*
KEY FEATURES:
- When user1 comes in all other available users will get a notification saying "user1 is joining now...".
- The new user will get a wecome message from server and will get to know how many available users are there and who are they.
- When a user types some message and press enter, the message will be sent to all the users (except the current user) via server.
- If user1 wants to chat with a specific user(say user2), he can type "Hello user2, blah blah..." and only user2 will get that message from user1.
- A user can type "LIST" to know the list of current users.
- A user can type "QUIT" in case he wants to leave chat.
- All the group-chat conversations and user connect/disconnect are logged into a log file at server side.
*/

public class ChatClient extends java.rmi.server.UnicastRemoteObject
    implements ChatClientInt, Runnable {

    private static final long serialVersionUID = 1L;

    private ChatServerInt server;
    private String name;
    private ChatClientInt friend;

    // comandos disponíveis:
    private static final String LIST = "LIST";
    private static final String QUIT = "QUIT";
    private static final String HELLO = "Hello ";

    public ChatClient(ChatServerInt cs, String name) throws RemoteException {
        this.name = name;
        this.server = cs;
        server.connect(name, this);
    }

    public synchronized void update(String name, String s) throws RemoteException {
        if ( !this.name.equals(name) )
            System.out.println(name + ": " + s);
    }

    public String getName() {
        return name;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        String msg;

        do {
            try {
                msg = in.nextLine().trim();

                if (QUIT.equals(msg)) {
                    server.disconnect(this);
                    in.close();
                    System.exit(0);

                } else if (LIST.equals(msg)) {
                    server.list(this);

                } else if (msg.startsWith(HELLO) && msg.contains(",")) {
                    String s[] = msg.substring(0, msg.indexOf(",")).split(" ");
                    String user = s[s.length-1].trim();
                    friend = server.lookup(user);
                    if (null != friend) friend.update(name, msg);
                    else server.broadcast(name, msg);

                } else if (! "".equals(msg)) {
                    server.broadcast(name, msg);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}