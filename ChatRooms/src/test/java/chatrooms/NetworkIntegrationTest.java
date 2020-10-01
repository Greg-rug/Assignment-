package chatrooms;

import chatrooms.model.botmanager.BotManager;
import chatrooms.model.chatroom.ChatRoom;
import chatrooms.model.server.Server;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkIntegrationTest {

    private static final int WAITING_TIME = 100;
    @Test
    void testApplication() throws InterruptedException {
        Server server = new Server();
        assertFalse(server.isRunning());
        server.start();
        Thread.sleep(WAITING_TIME);
        assertTrue(server.isRunning());

        ChatRoom chatRoom = new ChatRoom("Alfa");
        BotManager botManager = new BotManager(15);
    }
}
