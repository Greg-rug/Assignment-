package chatrooms.model.botmanager.bots;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatBotTest {

    @Test
    public void testConstructor() {
        RepeatBot bot = new RepeatBot("Adam", true);
        assertNotNull(bot);
        assertFalse(bot.isKill());
        assertTrue(bot.getMessageFeed().isEmpty());
        assertTrue(bot.isLocalBot());
        assertEquals("Adam", bot.getName());
    }
}
