package views.components;

import javax.swing.*;

public class SearchResultBox extends JPanel {
    private final String songName;
    private final String albumName;
    private final String artists;

    public SearchResultBox(String songName, String albumName, String artists) {
        this.songName = songName;
        this.artists = artists;
        this.albumName = albumName;

    }
}
