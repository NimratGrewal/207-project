package entities;

import java.time.LocalDate;

public class Song {
    private final String songId;
    private final String name;
    private final String artist;
    private final String album;
    private final LocalDate releaseDate;

    public Song(String songId, String name, String artist, String album, LocalDate releaseDate) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
    }

    public String getSongId() {
        return songId;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
