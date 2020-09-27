package chatrooms.view;

import chatrooms.controller.botmanager.KillAllButtonListener;
import chatrooms.model.botmanager.BotManager;

import javax.swing.*;

public class BotManagerPanel extends JPanel {

    private BotManager botManager;
    private final int GAP = 3;

    public BotManagerPanel(BotManager botManager) {
        this.botManager = botManager;
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(setButton());
    }

    private JButton setButton() {
        JButton button = new JButton("Kill all");
        button.addActionListener(new KillAllButtonListener(botManager));
        return button;
    }
}
