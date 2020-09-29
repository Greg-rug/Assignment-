package chatrooms.model.server;

import chatrooms.model.Feed;
import chatrooms.model.ConnectionHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT_NUMBER = 58965;
    private static final int MAX_CONNECTIONS = 50;

    private final Feed<Integer> portNumbers;
    private final Feed<String> messageFeed;
    private final ExecutorService executorService;

    /* DEMO questions
     * constants - load from file
     * chatroom remove port number from main server
    */

    public Server() {
        portNumbers = new Feed<>();
        messageFeed = new Feed<>();
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            messageFeed.add("Server successfully started at port " + PORT_NUMBER);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new ConnectionHandler(socket, messageFeed, portNumbers));
            }
        } catch (IOException e) {
            messageFeed.add("Connection error, server shuts down.");
        }
    }

    public Feed<String> getMessageFeed() {
        return messageFeed;
    }
}
