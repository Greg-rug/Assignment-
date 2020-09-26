package chatrooms.view;

import chatrooms.controller.botmanager.KillAllButtonListener;
import chatrooms.model.botmanager.BotManager;

import javax.swing.*;
import java.awt.*;

public class BotManagerPanel extends JPanel {

    private BotManager botManager;
    private final int GAP = 3;

    public BotManagerPanel(BotManager botManager) {
        this.botManager = botManager;
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(putInTitledScrollPane(constructBox()));
    }

    private JPanel putInTitledScrollPane(JComponent component) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createTitledBorder(""));
        wrapperPanel.add(new JScrollPane(component));
        return wrapperPanel;
    }

    private Box constructBox() {
        JButton sendButton = new JButton("Kill all");
        sendButton.addActionListener(new KillAllButtonListener(botManager));

        Box bh = Box.createHorizontalBox();
        bh.add(sendButton);
        return bh;
    }
}
