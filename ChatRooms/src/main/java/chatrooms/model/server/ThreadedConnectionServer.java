package chatrooms.model.server;

import chatrooms.controller.server.CommandHandler;
import chatrooms.controller.server.ClientTypeCommand;
import chatrooms.model.ThreadedConnection;
import chatrooms.model.Feed;
import chatrooms.model.Pair;
import java.net.Socket;

/**
 * this is a class for connections to the server
 */
public class ThreadedConnectionServer extends ThreadedConnection {

    private final Feed<Pair<String, Integer>> chatRooms;

    /**
     * Constructor
     * @param socket of the connection
     * @param messageFeed of the server
     * @param chatRooms collection of available chat rooms
     */
    public ThreadedConnectionServer(Socket socket, Feed<String> messageFeed, Feed<Pair<String, Integer>> chatRooms) {
        super(socket, messageFeed);
        this.chatRooms = chatRooms;
    }

    /**
     * Executes command if it exists for each type of client
     * @param line sent by client
     */
    @Override
    public void connection(String line) {
        String [] words = line.split(";");
        CommandHandler commandHandler = new CommandHandler(messageFeed, chatRooms, socket, words);
        ClientTypeCommand command = commandHandler.getCommand(words[0]);
        if (command != null) command.execute();
    }
}
