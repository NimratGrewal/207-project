package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotifyAPICallerTest {
    private SpotifyAPICaller c;
    @BeforeEach
    void init() {
        c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");
    }

    @Test
    void getTrack() {
        Song s = c.getTrack("11dFghVXANMlKmJXsNCbNl");
        assertEquals("Cut To The Feeling", s.getName());
        assertEquals("Cut To The Feeling", s.getAlbum());
        assertEquals(1, s.getArtists().size());
        assertEquals("11dFghVXANMlKmJXsNCbNl", s.getSongId());
        assertNotNull(s.getAlbumArt(10));
    }

    @Test
    void searchForTracks() {
        List<Song> songs = c.searchForTracks("may");
        assertTrue(songs.size() <= 5);
    }
}