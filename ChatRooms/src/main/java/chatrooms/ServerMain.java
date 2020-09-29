package chatrooms;


import chatrooms.model.server.*;
import chatrooms.view.SimpleFrame;

public class ServerMain {

	private static final String TITLE = "Main Server";

	public static void main(String[] args) {
		Server server = new Server();
		new SimpleFrame(server.getMessageFeed(), TITLE);
		server.start();

	}
}
