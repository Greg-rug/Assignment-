package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;

public class SpectateGameCommand extends MenuCommand {

    public SpectateGameCommand(GameServer gs) {
        super(gs);
    }

    @Override
    public void execute() {
        super.execute();
        gs.startClient();
        gs.getGame().setSpectate(true);
        gs.getGame().restart();
    }
}
