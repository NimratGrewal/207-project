package interface_adapter.view_response;

public class ViewResponseState {

    private String songName = "";
    private String artistNames = "";
    private String albumName = "";
    private String promptText = "";
    public ViewResponseState(ViewResponseState copy) {
        this.songName = copy.getSongName();
        this.artistNames = copy.getArtistNames();
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
}
