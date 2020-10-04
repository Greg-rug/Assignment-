package chatrooms.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {

    @Test
    public void testConstructor() {
        String first = "ALFA";
        String second = "BETA";
        Pair<String, String> pair = new Pair<>(first, second);
        assertNotNull(pair);
        assertEquals(first, pair.getFirst());
        assertEquals(second, pair.getSecond());
    }

}
