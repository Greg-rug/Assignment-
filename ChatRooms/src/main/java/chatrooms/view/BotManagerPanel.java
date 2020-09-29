package chatrooms.view;

import chatrooms.controller.botmanager.KillAllButtonListener;
import chatrooms.model.botmanager.BotManager;

import javax.swing.*;
import java.awt.*;

public class BotManagerPanel extends JPanel {

    private static final int WIDTH = 150;
    private static final int HEIGHT = (int) (WIDTH/1.618);

    public BotManagerPanel(BotManager botManager) {
        setLayout(new GridLayout(1,1));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        JButton button = new JButton("Kill all");
        button.addActionListener(new KillAllButtonListener(botManager));
        add(button);
    }
}
