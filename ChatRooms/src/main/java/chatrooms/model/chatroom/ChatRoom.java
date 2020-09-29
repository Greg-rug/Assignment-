package chatrooms.model.chatroom;

import chatrooms.model.Feed;
import chatrooms.model.ConnectionHandler;
import chatrooms.model.server.Server;
import chatrooms.view.TextFeedPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatRoom {

    private static final int MAX_CONNECTIONS = 20;

    private final Feed<String> messageFeed;
    private int portNumber;
    private final String name;
    private final ExecutorService executorService;

    public ChatRoom(String name) {
        this.name = name;
        messageFeed = new Feed<>();
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public boolean reportPortNumber() {
        Socket socketMS = new Socket();
        try {
            socketMS.connect(new InetSocketAddress("localhost", Server.PORT_NUMBER), 1000);
            if (!socketMS.isConnected()) throw new IOException();
            PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true);
            out.println("CHATROOM;" + name + ";" + portNumber);
            out.close();
            socketMS.close();
        } catch (IOException e) {
            messageFeed.add("Unable to make connection to main server.");
            return false;
        }
        return true;
    }

    public void start() {
        try (ServerSocket ss = new ServerSocket(0)) {
            portNumber = ss.getLocalPort();
            if (!reportPortNumber()) return;
            messageFeed.add("Chatroom " + name + " starts");
            while (true) {
                Socket socket = ss.accept();
                executorService.submit(new ConnectionHandler(socket, messageFeed));
            }
        } catch (IOException e) {
            messageFeed.add("Network error");
        }
    }

    public Feed<String> getMessageFeed() {
        return messageFeed;
    }
}
