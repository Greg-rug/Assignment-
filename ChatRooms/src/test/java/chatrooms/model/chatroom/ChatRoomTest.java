package chatrooms.model.chatroom;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChatRoomTest {

    @Test
    public void testConstructor() {
        ChatRoom chatRoom = new ChatRoom("Alfa");
        assertNotNull(chatRoom);
        assertFalse(chatRoom.isRunning());
        assertEquals("Alfa", chatRoom.getName());
        assertEquals(0, chatRoom.getPortNumber());
        assertTrue(chatRoom.getMessageFeed().isEmpty());
    }
}
