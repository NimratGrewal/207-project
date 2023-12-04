package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    private Song song;

    @BeforeEach
    void setUp() {
        String songId = "1234";
        String name = "Example Song";
        List<String> artists = new ArrayList<>();
        artists.add("Artist 1");
        artists.add("Artist 2");
        String album = "Example Album";
        Image albumArt = new ImageIcon("path/to/album/image.png").getImage();

        song = new Song(songId, name, artists, album, albumArt);
    }


    @Test
    void getSongId(){
        assertEquals("1234", song.getSongId());
    }

    @Test
    void getArtists() {
        List<String> artists = song.getArtists();
        assertNotNull(artists);
        assertEquals(2, artists.size());
        assertEquals("Artist 1", artists.get(0));
        assertEquals("Artist 2", artists.get(1));


    }

    @Test
    void getName() {
        assertEquals("Example Song", song.getName());

    }

    @Test
    void getAlbum() {
        assertEquals("Example Album", song.getAlbum());
    }

    @Test
    void getAlbumArt() {
        ImageIcon albumArt = song.getAlbumArt(100); // Assuming size 100x100
        assertNotNull(albumArt);
    }

    @AfterEach
    void tearDown() {
        song = null;
    }
}