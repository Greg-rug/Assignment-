package aoop.asteroids.control.menu.menu_commands;

import aoop.asteroids.control.menu.MenuItemCommand;

public class QuitCommand implements MenuItemCommand {

    @Override
    public void execute() {
        System.exit(0);
    }
}
