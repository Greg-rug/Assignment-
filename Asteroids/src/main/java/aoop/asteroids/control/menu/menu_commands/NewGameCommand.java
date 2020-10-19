package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.game.Game;

public class NewGameCommand implements MenuItemCommand {

    private final Game game;
    private final boolean asteroidsOnly;

    public NewGameCommand(Game game, boolean asteroidsOnly) {
        this.asteroidsOnly = asteroidsOnly;
        this.game = game;
    }

    @Override
    public void execute() {
        game.setAsteroidsOnly(asteroidsOnly);
        game.quit(); // Try to stop the game if it's currently running.
        game.initializeGameData(); // Resets the game's objects to their default state.
        game.start(); // Spools up the game's engine and starts the main game loop.
    }
}
