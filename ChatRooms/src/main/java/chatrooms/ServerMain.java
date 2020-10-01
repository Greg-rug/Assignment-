package chatrooms;


import chatrooms.model.server.*;
import chatrooms.view.SimpleFrame;

/**
 * Main class for main server
 */
public class ServerMain {

	/**
	 * title of the window
	 */
	private static final String TITLE = "Main Server";

	/**
	 * creates and starts the main server and its frame
	 * @param args no arguments used
	 */
	public static void main(String[] args) {
		Server server = new Server();
		new SimpleFrame(server.getMessageFeed(), TITLE);
		server.start();

	}
}
