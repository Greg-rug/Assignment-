package chatrooms.view;

import chatrooms.model.Feed;
import chatrooms.model.botmanager.BotManager;

import javax.swing.*;

public class SimpleFrame extends JFrame {

    public SimpleFrame(Feed<String> messageFeed, String title) {
        this(new TextFeedPanel(messageFeed, title), title);
    }

    public SimpleFrame(BotManager botManager, String title) {
        this(new BotManagerPanel(botManager), title);
    }

    public SimpleFrame(JPanel panel, String title) {
        super(title);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
