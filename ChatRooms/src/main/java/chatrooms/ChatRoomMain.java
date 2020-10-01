package chatrooms;

import chatrooms.model.chatroom.*;
import chatrooms.view.SimpleFrame;

/**
 * Main class for chat room
 */
public class ChatRoomMain {

    /**
     * Title of the window
     */
    private static final String TITLE = "Alfa";

    /**
     * creates and starts the chatroom and its frame
     * @param args no arguments used
     */
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom(TITLE);
        new SimpleFrame(chatRoom, chatRoom.getMessageFeed(), TITLE);
        chatRoom.start();
    }
}
