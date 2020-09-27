package chatrooms.model.server;

import chatrooms.model.ArrayListFeed;
import chatrooms.model.MessageFeed;
import chatrooms.model.ConnectionHandler;
import chatrooms.view.TextFeedPanel;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT_NUMBER = 58965;
    private static final int MAX_CONNECTIONS = 100;

    private final ArrayListFeed portNumbers;
    private final MessageFeed messageFeed;
    private final ExecutorService executorService;

    /* DEMO questions
     * constants,
     * executor service for each program
     * after clicking exit in chatroom remove port number from main server
     * switch statement in bot manager
     * minimalistic gui
     * bot inheritance
     * server shutting down
     * bot names enum
     * packages
     * to which package when it's used by both server and chatrooom (is it ok to join their packages?)
     * gui elements in model
     * console error output for bot manager
     * is bot manager supposed to shut down after kill all?
    */

    public Server() {
        portNumbers = new ArrayListFeed();
        messageFeed = new MessageFeed();
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void start() {
        setupGUI();
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            messageFeed.setMessage("Server successfully started at port " + PORT_NUMBER);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new ConnectionHandler(socket, messageFeed, portNumbers));
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
