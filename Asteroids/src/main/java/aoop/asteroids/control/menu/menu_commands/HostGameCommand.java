package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;

public class HostGameCommand extends MenuCommand {

    public HostGameCommand(GameServer gs) {
        super(gs);
    }

    @Override
    public void execute() {
        super.execute();
        gs.startServer();
        gs.getGame().setHost(true);
        gs.getGame().restart();
    }
}
