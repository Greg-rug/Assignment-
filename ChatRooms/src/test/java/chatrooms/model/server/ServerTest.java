package chatrooms.model.server;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

    @Test
    public void testConstructor() {
        Server server = new Server();
        assertNotNull(server);
        assertFalse(server.isRunning());
        assertTrue(server.getChatRooms().isEmpty());
        assertTrue(server.getMessageFeed().isEmpty());
    }
}
