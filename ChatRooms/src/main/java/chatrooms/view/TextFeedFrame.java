package chatrooms.view;

import chatrooms.model.Feed;

import javax.swing.*;

public class TextFeedFrame extends JFrame {

    public TextFeedFrame(Feed<String> messageFeed, String title) {
        super(title);
        TextFeedPanel panel = new TextFeedPanel(messageFeed, title);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
