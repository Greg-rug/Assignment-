package chatrooms;


import chatrooms.model.server.*;
import chatrooms.view.TextFeedFrame;

public class ServerMain {

	private static final String TITLE = "Main Server";

	public static void main(String args[]) {
		Server server = new Server();
		new TextFeedFrame(server.getMessageFeed(), TITLE);
		server.start();

	}
}
