package chatrooms;


import chatrooms.model.server.*;

public class ServerMain {

	public static void main(String args[]) {
		Server server = new Server();
		server.start();
	}
}
