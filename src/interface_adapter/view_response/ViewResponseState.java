package interface_adapter.view_response;

import javax.swing.*;

public class ViewResponseState {

    private String songName = "";
    private String artistNames = "";
    private ImageIcon albumCover;
    private String albumName = "";
    private String promptText = "";
    public ViewResponseState(ViewResponseState copy) {
        this.songName = copy.getSongName();
        this.artistNames = copy.getArtistNames();
        this.albumCover = copy.getAlbumCover();
        this.albumName = copy.getAlbumName();
        this.promptText = copy.getPromptText();
    }

    public ViewResponseState() {}

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistNames() {
        return artistNames;
    }

    public void setArtistNames(String artistNames) {
        this.artistNames = artistNames;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public void setAlbumCover(ImageIcon albumCover) {
        this.albumCover = albumCover;
    }

    public ImageIcon getAlbumCover() {
        return albumCover;
    }
}
