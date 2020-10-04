package chatrooms.model.botmanager;

import chatrooms.model.botmanager.bots.NormalBot;
import chatrooms.model.botmanager.bots.RandomBot;
import chatrooms.model.botmanager.bots.RepeatBot;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BotFactoryTest {

    BotFactory factory;

    @BeforeEach
    public void initialise() {
        factory = new BotFactory();
    }

    @Test
    public void testConstructor() {
        assertNotNull(factory);
    }

    @Test
    public void testGetRandomBot() {
        Bot bot1 = factory.getRandomBot();
        assertTrue(bot1 instanceof NormalBot || bot1 instanceof RandomBot || bot1 instanceof RepeatBot);
    }

    @Test
    public void testGetBot() {
        Bot bot1 = factory.getBot("Normal");
        assertTrue(bot1 instanceof NormalBot);
        bot1 = factory.getBot("Random");
        assertTrue(bot1 instanceof RandomBot);
        bot1 = factory.getBot("Repeat");
        assertTrue(bot1 instanceof RepeatBot);
    }
}
