package aoop.asteroids.model.online.NetworkCommands;

import aoop.asteroids.model.online.Client;
import aoop.asteroids.model.online.Connection;
import aoop.asteroids.model.online.Server;

/**
 * Command for ensuring that the server behaves according to receiving the spectate signal
 */
public class SpectateSignalCommand extends AbstractCommand {

    /**
     * Constructor
     * @param server server meant to implement the connections in the UDP network
     */
    public SpectateSignalCommand(Server server) {
        super(server);
    }

    /**
     * This execute function continues the run() method from Server class meant for implementing
     * the server behaviour for when the connection listened to receives the
     * spectate signal
     */
    @Override
    public void execute() {
        super.execute();
	server.run();
	if (server.findConnection(dp.getAddress()) == null) {
             connections.add(new Connection(game, ds, dp, -1));
        }
        send(ds, Client.RECEIVED_SIGNAL, 0, dp.getAddress(), dp.getPort());
    }
}
