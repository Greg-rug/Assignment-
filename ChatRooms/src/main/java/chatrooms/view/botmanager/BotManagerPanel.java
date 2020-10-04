package chatrooms.view.botmanager;

import chatrooms.controller.botmanager.KillAllListener;
import chatrooms.model.botmanager.BotManager;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for bot manager (1 big button to kill the bots)
 */
public class BotManagerPanel extends JPanel {

    /**
     * width of the panel
     */
    private static final int WIDTH = 150;

    /**
     * height of the panel (using golden ration)
     */
    private static final int HEIGHT = (int) (WIDTH/1.618);

    /**
     * Constructor
     * @param botManager botManager of the panel
     */
    public BotManagerPanel(BotManager botManager) {
        setLayout(new GridLayout(1,1));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        JButton button = new JButton("Kill all");
        button.addActionListener(new KillAllListener(botManager));
        add(button);
    }
}
