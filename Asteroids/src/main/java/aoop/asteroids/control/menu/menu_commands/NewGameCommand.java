package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;
import aoop.asteroids.model.game.Game;

public class NewGameCommand extends MenuCommand {

    private final boolean asteroidsOnly;

    public NewGameCommand(GameServer gs, boolean asteroidsOnly) {
        super(gs);
        this.asteroidsOnly = asteroidsOnly;
    }

    @Override
    public void execute() {
        super.execute();
        gs.getGame().setAsteroidsOnly(asteroidsOnly);
        gs.getGame().restart();
    }
}
