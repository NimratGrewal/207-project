package use_case.set_response;

import java.util.UUID;

public class SetResponseOutputData {
    private final String songName;
    private final String albumName;
    private final String artistNames;
    private final String promptText;

    public SetResponseOutputData(String songName, String albumName, String artistNames, String promptText) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistNames = artistNames;
        this.promptText = promptText;
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
}
