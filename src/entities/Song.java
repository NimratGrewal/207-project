package entities;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Song {
    private final String songId;
    private final String name;
    private final List<String> artists;
    private final String album;

    private final Image albumArt;

    /**
     * Creates an instance of Song
     *
     * @param songId  the Spotify ID of the song
     * @param name    the title of the song
     * @param artists the performing artists on the song
     * @param album   the album on which the song appears
     * @param albumArt the
     */
    public Song(String songId, String name, List<String> artists, String album, Image albumArt) {
        this.songId = songId;
        this.name = name;
        this.artists = artists;
        this.album = album;
        this.albumArt = albumArt;
    }

    /**
     *
     * @return the Spotify ID of this song
     */
    public String getSongId() {
        return songId;
    }

    /**
     *
     * @return list of names of the performing artists on this Song
     */
    public List<String> getArtists() {
        return artists;
    }

    /**
     *
     * @return name of this Song
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return name of the album this Song is on
     */
    public String getAlbum() {
        return album;
    }

    /**
     *
     * @param size
     * @return ImageIcon object of album art for this song scaled to size X size pixels
     */
    public ImageIcon getAlbumArt(int size) {
        return new ImageIcon(albumArt.getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }
}
