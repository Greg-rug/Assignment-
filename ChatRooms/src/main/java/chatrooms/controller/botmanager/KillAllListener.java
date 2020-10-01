package chatrooms.controller.botmanager;

import chatrooms.model.botmanager.BotManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a listener which kills all bots when the action is performed and then exits bot manager
 */
public class KillAllListener implements ActionListener {

    private final BotManager botManager;

    /**
     * Constructor
     * @param botManager whose bots will be killed when action is called
     */
    public KillAllListener(BotManager botManager) {
        this.botManager = botManager;
    }

    /**
     * This function exits disconnects all bots and then exits bot manager.
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        botManager.killAllBots();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            System.out.println("Interrupted");
        }
        System.exit(0);
    }
}
