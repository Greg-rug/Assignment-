package aoop.asteroids.control.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuItemAction extends AbstractAction {

    private final MenuCommandHandler menuCommandHandler;

    public MenuItemAction(MenuCommandHandler menuCommandHandler) {
        this.menuCommandHandler = menuCommandHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItemCommand command = menuCommandHandler.getCommand(e.getActionCommand());
        if (command != null) {
            command.execute();
            return;
        }
        command = menuCommandHandler.getCommand(MenuItem.findMenuItem(e.getActionCommand()));
        if (command != null) command.execute();
    }
}
