package chatrooms.model.chatroom;

import chatrooms.controller.server.ClientType;
import chatrooms.model.Feed;
import chatrooms.model.server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatRoom {

    /**
     * Signal send to the bots when the session is ending
     */
    public static final String END_SIGNAL = "Ending_the_session";

    private final Feed<String> messageFeed;
    private int portNumber;
    private final String name;
    private final ExecutorService executorService;

    /**
     * Constructor
     * @param name of the Chatroom
     */
    public ChatRoom(String name) {
        this.name = name;
        messageFeed = new Feed<>();
        executorService = Executors.newFixedThreadPool(Server.MAX_CONNECTIONS);
        portNumber = 0;
    }

    /**
     * starts the chatroom - to listen to the connections
     */
    public void start() {
        try (ServerSocket ss = new ServerSocket(portNumber)) {
            portNumber = ss.getLocalPort();
            if (!reportStatusToServer(ClientType.CHATROOM + ";" + name + ";" + portNumber)) return;
            messageFeed.add("Chatroom " + name + " starts");
            while (true) {
                Socket socket = ss.accept();
                executorService.submit(new ThreadedConnectionChatRoom(socket, messageFeed));
            }
        } catch (IOException e) {
            messageFeed.add("Network error");
        }
    }

    /**
     * reports message to the server
     * @param message to be reported
     * @return true if successful, false otherwise
     */
    private boolean reportStatusToServer(String message) {
        Socket socketMS = new Socket();
        try {
            socketMS.connect(new InetSocketAddress(Server.IP, Server.PORT_NUMBER), 1000);
            if (!socketMS.isConnected()) throw new IOException();
            PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true);
            out.println(message);
            out.close();
            socketMS.close();
        } catch (IOException e) {
            messageFeed.add("Unable to make connection to the main server.");
            return false;
        }
        closeSocket(socketMS);
        return true;
    }

    /**
     * report to the server that the chatroom is closing
     */
    public void endSession() {
        reportStatusToServer(ClientType.CHATROOM + ";" + name + ";" + ThreadedConnectionChatRoom.DISCONNECT_SIGNAL);
        messageFeed.add(END_SIGNAL);
    }

    /**
     * closes the socket if it's open
     * @param socket to be closed
     */
    private void closeSocket(Socket socket) {
        try {
            if (socket.isConnected()) socket.close();
        } catch (IOException e) {
            messageFeed.add("Unable to close the socket.");
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
