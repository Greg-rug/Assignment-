package chatrooms.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is a class to deal with connection from the client
 */
public abstract class ThreadedConnection implements Runnable {

    protected final Socket socket;
    protected final Feed<String> messageFeed;

    /**
     * Constructor
     * @param socket of connection
     * @param messageFeed of the server
     */
    public ThreadedConnection(Socket socket, Feed<String> messageFeed){
        this.socket = socket;
        this.messageFeed = messageFeed;
    }

    /**
     * Method that needs to be implemented for children of this class to continue dealing with the connection
     * @param line message sent by client
     * @throws IOException connection problem
     */
    public abstract void connection(String line) throws IOException;

    /**
     * reads the line send by client and calls connection method
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();
            if (line != null) connection(line);
        } catch (IOException e) {
            messageFeed.add("Connection problem with the client");
        }
        closeSocket(socket);
    }

    /**
     * closes the socket if it's open
     * @param socket of connection
     */
    protected void closeSocket(Socket socket) {
        try {
            if (socket.isConnected()) socket.close();
        } catch (IOException e) {
            messageFeed.add("Cannot close socket");
        }
    }

}
