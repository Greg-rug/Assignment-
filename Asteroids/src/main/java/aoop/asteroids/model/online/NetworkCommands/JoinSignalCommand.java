package aoop.asteroids.model.online.NetworkCommands;

import aoop.asteroids.model.online.Client;
import aoop.asteroids.model.online.Connection;
import aoop.asteroids.model.online.Server;

/**
 * Command for ensuring that the server behaves according to receiving the join signal
 */
public class JoinSignalCommand extends AbstractCommand {

    /**
     * Constructor
     * @param server server meant to implement the connections in the UDP network
     */
    public JoinSignalCommand(Server server) {
        super(server);
    }

    /**
     * This execute function continues the run() method from Server class meant for implementing
     * the server behaviour for when the connection listened to receives the
     * join signal
     */
    @Override
    public void execute() {
        super.execute();
	    server.run();
        if (server.findConnection(dp.getAddress()) == null) {
              shipID = game.addSpaceShip();
              connections.add(new Connection(game, ds, dp, shipID));
        }
        send(ds, Client.RECEIVED_SIGNAL, shipID, dp.getAddress(), dp.getPort());
    }
}
