package chatrooms;

import chatrooms.model.chatroom.*;
import chatrooms.view.SimpleFrame;

public class ChatRoomMain {

    private static final String TITLE = "Alfa";

    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom(TITLE);
        new SimpleFrame(chatRoom.getMessageFeed(), TITLE);
        chatRoom.start();
    }
}
