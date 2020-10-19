package aoop.asteroids.control.menu;

import aoop.asteroids.util.Pair;
import aoop.asteroids.view.AsteroidsPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class MenuMouseController extends MouseInputAdapter {

    private final AsteroidsPanel asteroidsPanel;

    public MenuMouseController(AsteroidsPanel asteroidsPanel) {
        this.asteroidsPanel = asteroidsPanel;
        asteroidsPanel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Pair<Shape, MenuItem> pair: asteroidsPanel.getMenuItems()) {
            if (asteroidsPanel.getGame().isAsteroidsOnly() && pair.getFirst().contains(e.getPoint())) {
                new MenuItemAction(asteroidsPanel.getCommandHandler()).actionPerformed(
                        new ActionEvent(asteroidsPanel, ActionEvent.ACTION_PERFORMED, pair.getSecond().toString()));
            }
        }
    }
}
