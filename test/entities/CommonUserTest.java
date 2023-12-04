package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser newUser;
    private CommonUser existingUser;
    private final UUID existingUUID = UUID.randomUUID();
    private final LocalDateTime date = LocalDateTime.now();
    private UUID promptId;
    private Response response;

    @BeforeEach
    void setUp() {
        newUser = new CommonUser("Nimrat", "password");
        existingUser = new CommonUser(existingUUID,"haya", "1234jkl");

        promptId = UUID.randomUUID();
        UUID responseId = UUID.randomUUID();
        List<String> artist = new ArrayList<>();
        artist.add("Shakira");
        // Creating a response for testing
        response = new Response(responseId, promptId, existingUUID, new Song("songId",
                "hips don't lie", artist, "some album", null));
    }

    @AfterEach
    void tearDown() {
        // Clean up after tests if needed
        newUser = null;
        existingUser = null;
        // Other cleanup tasks
    }

    @Test
    void getUsername() {
        assertEquals("Nimrat", newUser.getUsername());
        assertEquals("haya", existingUser.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("password", newUser.getPassword());
        assertEquals("1234jkl", existingUser.getPassword());
    }

    @Test
    void getUserId() {
        assertEquals(existingUUID, existingUser.getUserId());
    }

    @Test
    void getHistory() {
        assertEquals(new HashMap<>(), newUser.getHistory());
        assertEquals(new HashMap<>(), existingUser.getHistory());
    }

    @Test
    void getResponse() {
        newUser.setResponse(promptId, response);
        assertEquals(response, newUser.getResponse(promptId));
    }

    @Test
    void setResponse() {
        newUser.setResponse(promptId, response);
        assertEquals(response, newUser.getResponse(promptId));
    }

    @Test
    void deleteResponse() {
        newUser.setResponse(promptId, response);
        assertTrue(newUser.getHistory().containsKey(promptId));
        newUser.deleteResponse(promptId);
        assertFalse(newUser.getHistory().containsKey(promptId));
    }
}
