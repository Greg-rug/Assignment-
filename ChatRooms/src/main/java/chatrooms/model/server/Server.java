package chatrooms.model.server;

import chatrooms.model.ArrayListFeed;
import chatrooms.model.MessageFeed;
import chatrooms.model.ThreadedConnectionHandler;
import chatrooms.view.TextFeedPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT_NUMBER = 58965;
    private final ArrayListFeed portNumbers;
    private static final int MAX_CONNECTIONS = 100;
    private final MessageFeed messageFeed;
    private final ExecutorService executorService;

    public Server() {
        portNumbers = new ArrayListFeed();
        messageFeed = new MessageFeed();
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void start() {
        setupGUI();
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            messageFeed.setMessage("Server successfully started at port " + PORT_NUMBER);

            //is server ever suppose to shutdown?
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new ThreadedConnectionHandler(socket, messageFeed, portNumbers));
            }
        } catch (IOException e) {
            messageFeed.setMessage("Connection error, server shuts down.");
        }
        System.exit(0);
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            TextFeedPanel panel = new TextFeedPanel(messageFeed, "Main Server");
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }



}
