package chatrooms.model.chatroom;

import chatrooms.controller.server.ClientType;
import chatrooms.model.ThreadedConnection;
import chatrooms.model.Feed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * class that handles the connection of bot to he chatroom
 */
public class ThreadedConnectionChatRoom extends ThreadedConnection implements PropertyChangeListener {

    /**
     * Signal to be send, when bot disconnects
     */
    public static final String DISCONNECT_SIGNAL = "DISCONNECT_ME";

    private final PrintWriter out;

    /**
     * Constructor
     * @param socket socket connected to bot
     * @param messageFeed of chatroom
     * @throws IOException connectio problem
     */
    public ThreadedConnectionChatRoom(Socket socket, Feed<String> messageFeed) throws IOException {
        super(socket, messageFeed);
        out  = new PrintWriter(socket.getOutputStream(), true);
        messageFeed.addListener(this);
    }

    /**
     * Sends added message (to message feed) to the bot connected to the socket
     * @param evt added message to the message feed
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        out.println(messageFeed.getLast());
    }

    /**
     * adds messages send by bot to the message feed
     * @param name of bot
     * @throws IOException connection problem
     */
    @Override
    public void connection(String name) throws IOException {
        String line;
        messageFeed.add(ClientType.BOT + " " + name + " has joined the chatroom");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while ((line = in.readLine()) != null && !line.equals(DISCONNECT_SIGNAL)) {
            messageFeed.add(ClientType.BOT + " " + name + ": " + line);
        }
        messageFeed.add(ClientType.BOT + " " + name + " has disconnected");
    }
}
