package chatrooms.model.botmanager;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BotManagerTest {

    @Test
    public void testConstructor() {
        BotManager botManager = new BotManager(15);
        assertNotNull(botManager);
    }
}
