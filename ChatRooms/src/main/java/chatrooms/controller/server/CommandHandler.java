package chatrooms.controller.server;

import chatrooms.controller.server.commands.BotCommand;
import chatrooms.controller.server.commands.ChatRoomCommand;
import chatrooms.model.Feed;
import chatrooms.model.Pair;

import java.net.Socket;
import java.util.HashMap;

/**
 * this class is a handler for commands of server
 */
public class CommandHandler {

    private final HashMap<String, ClientTypeCommand> commandMap;

    /**
     * Constructor
     * @param messageFeed of server
     * @param chatRooms collection of chatroom names and portnumbers that are currently listening to connections
     * @param socket that is connected to server
     * @param words data send from the client of server
     */
    public CommandHandler(Feed<String> messageFeed, Feed<Pair<String, Integer>> chatRooms,
                          Socket socket, String[] words) {
        commandMap = new HashMap<>();
        if (words.length > 1) {
            commandMap.putIfAbsent(ClientType.BOT,
                    new BotCommand(messageFeed, chatRooms, socket, words[1]));
            if (words.length > 2) commandMap.putIfAbsent(ClientType.CHATROOM,
                    new ChatRoomCommand(messageFeed, chatRooms, words[1], words[2]));
        }

    }

    /**
     * Getter for command from the map
     * @param command name of command
     * @return corresponding command
     */
    public ClientTypeCommand getCommand(String command) {
        return commandMap.get(command);
    }
}
