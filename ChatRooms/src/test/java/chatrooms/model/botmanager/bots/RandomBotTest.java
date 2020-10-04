package chatrooms.model.botmanager.bots;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {

    @Test
    public void testConstructor() {
        RandomBot bot = new RandomBot("Adam", true);
        assertNotNull(bot);
        assertFalse(bot.isKill());
        assertTrue(bot.getMessageFeed().isEmpty());
        assertTrue(bot.isLocalBot());
        assertEquals("Adam", bot.getName());
    }
}
