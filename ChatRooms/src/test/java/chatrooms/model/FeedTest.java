package chatrooms.model;

import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FeedTest {

    private Feed<String> feed;
    private String s1;
    private String s2;

    @BeforeEach
    public void initialise() {
        feed = new Feed<>();
        s1 = "ONE";
        s2 = "TWO";
    }

    @Test
    public void testConstructor() {
        assertNotNull(feed);
        assertTrue(feed.isEmpty());
    }

    @Test
    public void testGet() {
        //normal case
        feed.add(s1);
        assertEquals(s1, feed.get(0));
        //out of bounds case
        assertThrows(IndexOutOfBoundsException.class, () -> feed.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> feed.get(-1));

    }

    @Test
    public void testGetLast() {
        //empty case
        assertNull(feed.getLast());
        //normal case
        feed.add(s1);
        assertNotEquals(s2, feed.getLast());
        feed.add(s2);
        assertEquals(s2, feed.getLast());
    }

    @Test
    public void TestGetLastElements() {
        //normal case
        feed.add(s1);
        feed.add(s2);
        ArrayList<String> strings = feed.getLastElements(2);
        assertTrue(strings.contains(s1));
        assertTrue(strings.contains(s2));
        //over bounds case
        strings = feed.getLastElements(100);
        assertTrue(strings.contains(s1));
        assertTrue(strings.contains(s2));
        assertEquals(2, strings.size());
    }

    @Test
    public void testGetRandom() {
        //test on empty list
        assertNull(feed.getRandom());
        //normal case
        feed.add(s1);
        feed.add(s2);
        String s = feed.getRandom();
        if (s.equals(s1)) assertEquals(s1, s);
        else assertEquals(s2, s);
    }

    @Test
    public void testAdd() {
        //normal case
        assertFalse(feed.contains(s1));
        feed.add(s1);
        assertTrue(feed.contains(s1));
    }

    @Test
    public void testRemove() {
        //normal case
        assertFalse(feed.contains(s1));
        feed.add(s1);
        assertTrue(feed.contains(s1));
        feed.remove(s1);
        assertFalse(feed.contains(s1));
        //not in set case
        assertFalse(feed.remove(s1));
    }

    @Test
    public void testRemove2() {
        //normal case
        assertFalse(feed.contains(s1));
        feed.add(s1);
        assertTrue(feed.contains(s1));
        assertEquals(s1, feed.remove(0));
        assertFalse(feed.contains(s1));
        //out of bounds case
        assertThrows(IndexOutOfBoundsException.class, () -> feed.get(0));
    }
}
