package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;

public class JoinGameCommand extends MenuCommand {

    public JoinGameCommand(GameServer gs) {
        super(gs);
    }

    @Override
    public void execute() {
        super.execute();
        gs.startClient();
        gs.getGame().setClient(true);
        gs.getGame().restart();
    }
}
