package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;
import chatrooms.model.ThreadedConnectionHandler;
import chatrooms.model.server.Server;
import chatrooms.view.TextFeedPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoom {

    private final MessageFeed messageFeed;
    private int portNumber;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
        messageFeed = new MessageFeed();
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
            messageFeed.setMessage("Unable to make connection to main server.");
            return false;
        }
        return true;
    }

    public void start() {
        setupGUI();
        try (ServerSocket ss = new ServerSocket(0)) {
            portNumber = ss.getLocalPort();
            if (!reportPortNumber()) return;
            messageFeed.setMessage("Chatroom " + name + " starts");
            while (true) {
                Socket socket = ss.accept();
                Thread t = new Thread(new ThreadedConnectionHandler(socket, messageFeed));
                t.start();
            }
        } catch (IOException e) {
            messageFeed.setMessage("Network error");
        }
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            TextFeedPanel panel = new TextFeedPanel(messageFeed, name);
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
