package use_case.set_response;

import javax.swing.*;

public class SetResponseOutputData {
    private final String songName;
    private final String albumName;
    private final String artistNames;
    private final String promptText;
    private final ImageIcon albumArt;

    public SetResponseOutputData(String songName, String albumName, String artistNames, String promptText, ImageIcon albumArt) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistNames = artistNames;
        this.promptText = promptText;
        this.albumArt = albumArt;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getPromptText() {
        return promptText;
    }

    public String getArtistNames() {
        return artistNames;
    }

    public ImageIcon getAlbumArt() {return albumArt; }
}
