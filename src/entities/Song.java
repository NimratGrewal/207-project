package entities;

import java.time.LocalDate;
import java.util.List;

public class Song {
    private final String songId;
    private final String name;
    private final List<String> artists;
    private final String album;
    private final LocalDate releaseDate;

    /**
     * Creates an instance of Song
     *
     * @param songId the Spotify ID of the song
     * @param name the title of the song
     * @param artists the performing artists on the song
     * @param album the album on which the song appears
     * @param releaseDate the release date of the album the song is on
     */
    public Song(String songId, String name, List<String> artists, String album, LocalDate releaseDate) {
        this.songId = songId;
        this.name = name;
        this.artists = artists;
        this.album = album;
        this.releaseDate = releaseDate;
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
     * @return release date of this song
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
