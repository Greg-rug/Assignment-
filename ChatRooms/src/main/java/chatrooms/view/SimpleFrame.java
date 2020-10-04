package chatrooms.view;

import chatrooms.controller.chatroom.ExitListener;
import chatrooms.model.Feed;
import chatrooms.model.botmanager.BotManager;
import chatrooms.model.chatroom.ChatRoom;
import chatrooms.view.botmanager.BotManagerPanel;

import javax.swing.*;

/**
 * Simple JFrame with few setting used by all 3 programs
 */
public class SimpleFrame extends JFrame {

    /**
     * Constructor used by main server
     * @param messageFeed message feed of the main server
     * @param title of the window
     */
    public SimpleFrame(Feed<String> messageFeed, String title) {
        this(new TextFeedPanel(messageFeed, title), title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Constructor used by bot manager
     * @param botManager used for bot manager panel
     * @param title of the window
     */
    public SimpleFrame(BotManager botManager, String title) {
        this(new BotManagerPanel(botManager), title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Constructor used by chat room
     * @param chatRoom used to add window listener
     * @param messageFeed message feed of the main server
     * @param title of the window
     */
    public SimpleFrame(ChatRoom chatRoom, Feed<String> messageFeed, String title) {
        this(new TextFeedPanel(messageFeed, title), title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitListener(chatRoom));
    }

    /**
     * Simple constructor used by all 3 programs
     * @param panel to be added to the frame
     * @param title of the window
     */
    public SimpleFrame(JPanel panel, String title) {
        super(title);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
