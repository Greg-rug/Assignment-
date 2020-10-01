package chatrooms.controller.server.commands;

import chatrooms.controller.server.ClientType;
import chatrooms.controller.server.ClientTypeCommand;
import chatrooms.model.Feed;
import chatrooms.model.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is a class for server how to deal with BOT connection
 */
public class BotCommand implements ClientTypeCommand {

    private final Feed<String> messageFeed;
    private final Feed<Pair<String, Integer>> chatRooms;
    private final Socket socket;
    private final String name;

    /**
     * Constructor
     * @param messageFeed of server
     * @param chatRooms collection of chatroom names and port numbers
     * @param socket that is connected to bot
     * @param name name of bot
     */
    public BotCommand(Feed<String> messageFeed, Feed<Pair<String, Integer>> chatRooms, Socket socket, String name) {
        this.messageFeed = messageFeed;
        this.chatRooms = chatRooms;
        this.socket = socket;
        this.name = name;
    }

    /**
     * sends random port number of chatroom to the connecting bot
     */
    @Override
    public void execute() {
        int portNumber = chatRooms.getRandom().getSecond();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(portNumber);
        } catch (IOException e) {
            messageFeed.add("Cannot reach the Bot");
            return;
        }
        messageFeed.add(ClientType.BOT + " " + name + " is joining the room at " + portNumber);
    }
}
