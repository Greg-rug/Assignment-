package aoop.asteroids;

import aoop.asteroids.control.menu.MenuCommandHandler;
import aoop.asteroids.control.menu.MenuItem;
import aoop.asteroids.control.menu.MenuItemAction;
import aoop.asteroids.model.GameServer;
import aoop.asteroids.model.game.Game;
import aoop.asteroids.model.online.Client;
import aoop.asteroids.model.online.Server;
import aoop.asteroids.util.ByteUtil;
import aoop.asteroids.view.AsteroidsFrame;

import java.awt.event.ActionEvent;
import java.io.*;

public class TestMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        /*
        Game g1 = new Game();
        Server s = new Server(g1);
        g1.start();
        Thread t1 = new Thread(s);
        t1.start();
        Thread.sleep(100);
        Client c = new Client(new Game());
        Thread t2 = new Thread(c);
        t2.start();

         */



        Game g = new Game();
        g.start();
        Thread.sleep(10000);
        ByteUtil bytes = new ByteUtil();
        bytes.add(g);
        g.quit();
        Game f = new Game();
        f.loadGame(bytes.getByteArray());
        f.quit();
        int i = 8;


        /*
        Game g = new Game();
        Client client = new Client(g);
        client.run();

        AsteroidsFrame frame = new AsteroidsFrame(g);
        // Generate a new action event so that we can use the NewGameAction to start a new game.
        new MenuItemAction(new MenuCommandHandler(g)).actionPerformed(
                new ActionEvent(frame, ActionEvent.ACTION_PERFORMED, MenuItem.MAIN_MENU.toString()));
         */


    }
}
