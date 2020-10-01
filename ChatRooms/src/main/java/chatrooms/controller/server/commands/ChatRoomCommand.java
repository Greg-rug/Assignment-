package chatrooms.controller.server.commands;

import chatrooms.controller.server.ClientType;
import chatrooms.controller.server.ClientTypeCommand;
import chatrooms.model.Feed;
import chatrooms.model.Pair;
import chatrooms.model.chatroom.ThreadedConnectionChatRoom;

/**
 * This is a class for server to deal with connection from chatroom
 */
public class ChatRoomCommand implements ClientTypeCommand {

    private final Feed<String> messageFeed;
    private final Feed<Pair<String, Integer>> chatRooms;
    private final String name;
    private final String port;

    /**
     * Constructor
     * @param messageFeed of server
     * @param chatRooms collection of chatroom names and port numbers
     * @param name of connecting chatroom
     * @param port of connecting chatroom
     */
    public ChatRoomCommand(Feed<String> messageFeed, Feed<Pair<String, Integer>> chatRooms, String name, String port) {
        this.messageFeed = messageFeed;
        this.chatRooms = chatRooms;
        this.name = name;
        this.port = port;
    }

    /**
     * If chatroom sends disconnect signal, it removes it port number from the collection
     * If chatroom sends portnumber its port number, then it's added to the chatRooms collection
     */
    @Override
    public void execute() {
        if (port.equals(ThreadedConnectionChatRoom.DISCONNECT_SIGNAL)) {
            for (Pair<String, Integer> pair: chatRooms) {
                if (pair.getFirst().equals(name)) {
                    int chatRoomPortNumber = pair.getSecond();
                    chatRooms.remove(pair);
                    messageFeed.add(name + " at " + chatRoomPortNumber + " is no longer available.");
                    break;
                }
            }
            return;
        }
        try {
            if (chatRooms.add(new Pair<>(name, Integer.parseInt(port)))) {
                messageFeed.add(name + " is listening at port " + port);
            }
        } catch (NumberFormatException nfe) {
            messageFeed.add(ClientType.CHATROOM + " " + name + " sent incorrectly formatted string, unable to connect.");
        }
    }
}
