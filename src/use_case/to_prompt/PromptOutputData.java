package use_case.to_prompt;

import javax.swing.*;

public class PromptOutputData {
    private final String songName;
    private final String albumName;
    private final String artistNames;
    private final String promptText;
    private final ImageIcon albumArt;

    public PromptOutputData(String songName, String albumName, String artistNames, String promptText, ImageIcon albumArt) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistNames = artistNames;
        this.promptText = promptText;
        this.albumArt = albumArt;
    }

    public PromptOutputData(String promptText) {
        this.promptText = promptText;
        this.songName = null;
        this.albumName = null;
        this.artistNames = null;
        this.albumArt = null;
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
