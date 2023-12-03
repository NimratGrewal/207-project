package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    private Response response;
    UUID promptId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    ImageIcon albumArt = new ImageIcon("placeholderAlbum");
    List<String> artists = new ArrayList<String>() {{
        add("Mitski");
    }};
    Song song = new Song("10", "My Love Mine All Mine", artists,
            "This Land is Inhospitable and So Are We", albumArt);

    @BeforeEach
    void init() {
        response = new Response(promptId, userId, song);
    }

    @Test
    void getResponseIdTest() {
        assertNotNull(response.getResponseId());
    }

    @Test
    void getPromptIdTest() {
        assertEquals(promptId, response.getPromptId());
    }

    @Test
    void getUserIdTest() {
        assertEquals(userId, response.getUserId());
    }

    @Test
    void getSongTest() {
        assertEquals(song, response.getSong());
    }
}