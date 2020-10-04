package chatrooms.controller.chatroom;

import chatrooms.model.chatroom.ChatRoom;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class makes sure the chatroom sends disconnect information before closing
 */
public class ExitListener extends WindowAdapter {

    private final ChatRoom chatRoom;

    /**
     * Constructor
     * @param chatRoom on which is window adapter
     */
    public ExitListener(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    /**
     * when the exit button is pressed, this is action is called first before exiting.
     * Chatroom sends disconnect signal to the main server.
     * @param e - exit event
     */
    @Override
    public void windowClosing(WindowEvent e) {
        chatRoom.endSession();
        System.exit(0);
    }
}
