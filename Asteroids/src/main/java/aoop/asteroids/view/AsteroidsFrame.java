package aoop.asteroids.view;

import aoop.asteroids.control.*;
import aoop.asteroids.control.menu.MenuItem;
import aoop.asteroids.control.menu.MenuItemAction;
import aoop.asteroids.control.menu.MenuMouseController;
import aoop.asteroids.model.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * The main window that's used for displaying the game.
 */
public class AsteroidsFrame extends JFrame {
	/**
	 * The title which appears in the upper border of the window.
	 */
	private static final String WINDOW_TITLE = "Asteroids";

	/**
	 * The size that the window should be.
	 */
	public static final Dimension WINDOW_SIZE = new Dimension(800, 800);

	/** The game model. */
	private final Game game;

	/**
	 * Constructs the game's main window.
	 *
	 * @param game The game model that this window will show.
	 */
	public AsteroidsFrame (Game game) {
		this.game = game;
		initSwingUI();
	}

	/**
	 * A helper method to do the tedious task of initializing the Swing UI components.
	 */
	private void initSwingUI() {
		// Basic frame properties.
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		AsteroidsPanel panel = new AsteroidsPanel(game);

		// Add a key listener that can control the game's spaceship.
		addKeyListener(new PlayerKeyListener(game.getSpaceship()));

		// Add a menu bar with some simple actions.
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menuBar.add(menu);


		for (MenuItem menuItem: MenuItem.getAllItems()) {
			MenuItemAction action = new MenuItemAction(panel.getCommandHandler());
			action.putValue(Action.NAME, menuItem.getTitle());
			menu.add(action);
		}
		setJMenuBar(menuBar);
		new MenuMouseController(panel);

		// Add the custom panel that the game will be drawn to.
		add(panel);
		setVisible(true);
	}
}
