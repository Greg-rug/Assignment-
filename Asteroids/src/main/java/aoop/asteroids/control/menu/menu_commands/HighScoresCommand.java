package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;
import aoop.asteroids.model.game.Game;

public class HighScoresCommand extends MenuCommand {

    public HighScoresCommand(GameServer gs) {
        super(gs);
    }

    @Override
    public void execute() {
        boolean mainMenu = gs.getGame().isAsteroidsOnly();
        super.execute();
        gs.getGame().setAsteroidsOnly(true);
        gs.setHighScores(true);
        if (!mainMenu) {
            gs.getGame().restart();
        }
    }
}
