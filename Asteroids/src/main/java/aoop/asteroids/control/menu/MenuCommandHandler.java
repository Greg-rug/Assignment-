package aoop.asteroids.control.menu;

import aoop.asteroids.model.GameServer;
import aoop.asteroids.model.game.Game;
import aoop.asteroids.control.menu.menu_commands.*;

import java.util.HashMap;

public class MenuCommandHandler {

    private final HashMap<MenuItem, MenuItemCommand> menuCommandsMap;
    private final HashMap<String, MenuItem> stringMenuItemMap;

    public MenuCommandHandler(GameServer gameServer) {
        menuCommandsMap = new HashMap<>();
        stringMenuItemMap = new HashMap<>();
        for (MenuItem item: MenuItem.getAllItems()) {
            stringMenuItemMap.putIfAbsent(item.toString(), item);
        }
        menuCommandsMap.putIfAbsent(MenuItem.NEW_GAME, new NewGameCommand(gameServer, false));
        menuCommandsMap.putIfAbsent(MenuItem.JOIN, new JoinGameCommand(gameServer));
        menuCommandsMap.putIfAbsent(MenuItem.HOST, new HostGameCommand(gameServer));
        menuCommandsMap.putIfAbsent(MenuItem.SPECTATE, new SpectateGameCommand(gameServer));
        menuCommandsMap.putIfAbsent(MenuItem.HIGH_SCORES, new HighScoresCommand(gameServer));
        menuCommandsMap.putIfAbsent(MenuItem.MAIN_MENU, new NewGameCommand(gameServer, true));
        menuCommandsMap.putIfAbsent(MenuItem.QUIT, new QuitCommand());
    }

    public MenuItemCommand getCommand(MenuItem command) {
        return menuCommandsMap.get(command);
    }

    public MenuItemCommand getCommand(String command) {
        MenuItem item = stringMenuItemMap.get(command);
        if (item != null) return menuCommandsMap.get(item);
        return null;
    }
}
