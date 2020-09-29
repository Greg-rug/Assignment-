package chatrooms;

import chatrooms.model.chatroom.*;
import chatrooms.view.TextFeedFrame;

public class ChatRoomMain {

    private static final String TITLE = "Alfa";

    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom(TITLE);
        new TextFeedFrame(chatRoom.getMessageFeed(), TITLE);
        chatRoom.start();
    }
}
