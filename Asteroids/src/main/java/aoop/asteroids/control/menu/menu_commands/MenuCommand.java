package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;
import aoop.asteroids.model.GameServer;

public abstract class MenuCommand implements MenuItemCommand {

    protected final GameServer gs;

    public MenuCommand(GameServer gs) {
        this.gs = gs;
    }

    public void execute() {
        gs.reset();
    }
}
