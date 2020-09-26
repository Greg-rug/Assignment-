package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;
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
    private static final int portNumberMS = 6543;

    public ChatRoom(String name) {
        this.name = name;
        messageFeed = new MessageFeed();
    }

    public boolean reportPortNumber() {
        Socket socketMS = new Socket();
        try {
            socketMS.connect(new InetSocketAddress("localhost", portNumberMS), 1000);
            if (!socketMS.isConnected()) throw new IOException();
        } catch (IOException e) {
            messageFeed.setMessage("Unable to make connection to main server.");
            return false;
        }
        try (PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true)) {

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
            if (!reportPortNumber()) {
                messageFeed.setMessage("Unable to make connection");
                return;
            }
            messageFeed.setMessage("Welcome!");

            while (true) {
                Socket socket = ss.accept();
                Thread t = new Thread(new ThreadedBotHandler(socket, messageFeed));
                t.start();
                //t = new Thread(new ThreadedBotSender(socket, messageFeed));
                //t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            TextFeedPanel panel = new TextFeedPanel(messageFeed);
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
