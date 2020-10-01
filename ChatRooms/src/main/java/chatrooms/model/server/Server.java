package chatrooms.model.server;

import chatrooms.model.Feed;
import chatrooms.model.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for main server
 */
public class Server {

    /**
     * port number of main server
     */
    public static final int PORT_NUMBER = 58965;

    /**
     * IP address of the main server
     */
    public static final String IP = "localhost";

    /**
     * Maximum number of connection server can handle
     */
    public static final int MAX_CONNECTIONS = 20;

    private final Feed<Pair<String, Integer>> chatRooms;
    private final Feed<String> messageFeed;
    private final ExecutorService executorService;
    private boolean running;

    /**
     * Constructor
     */
    public Server() {
        chatRooms = new Feed<>();
        messageFeed = new Feed<>();
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        running = false;
    }

    /**
     * starts the server, server waits for connections
     */
    public void start() {
        running = true;
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            messageFeed.add("Server successfully started at port " + PORT_NUMBER);
            while (running) {
                Socket socket = serverSocket.accept();
                executorService.submit(new ThreadedConnectionServer(socket, messageFeed, chatRooms));
            }
        } catch (IOException e) {
            messageFeed.add("Connection error, server shuts down.");
        }
    }

    /**
     * getter for message feed
     * @return message feed
     */
    public Feed<String> getMessageFeed() {
        return messageFeed;
    }
}
