package chatrooms.controller.botmanager;

import chatrooms.model.botmanager.BotManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KillAllButtonListener implements ActionListener {

    private final BotManager botManager;

    public KillAllButtonListener(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        botManager.killAllBots();
    }
}
