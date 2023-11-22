package views;

import entities.Response;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResponseBox extends JPanel {

    public ResponseBox(Response response) {

        String username = response.getUser().getUsername();
        String songName = response.getSong().getName();
        List<String> songArtists = response.getSong().getArtists();
        String songAlbum = response.getSong().getAlbum();
        String albumCoverUrl = response.getSong().getAlbumCoverUrl();

        ImageIcon albumCover = new ImageIcon(albumCoverUrl);

        setLayout(new BorderLayout());

        // user panel
        JPanel userPanel = new JPanel(new BorderLayout());
        JLabel usernameLabel = new JLabel( username + "said");
        userPanel.add(usernameLabel, BorderLayout.NORTH);

        //album cover?
        JPanel albumPanel = new JPanel(new BorderLayout());
        JLabel albumCoverLabel = new JLabel(albumCoverUrl);
        albumCoverLabel.setIcon(albumCover);
        albumPanel.add(albumCoverLabel, BorderLayout.CENTER);

         // song details
        JPanel songPanel = new JPanel(new GridLayout(3, 1));

        JLabel songLabel = new JLabel("Song: " + songName);
        JLabel artistLabel = new JLabel("Artist: " + songArtists);
        JLabel albumLabel = new JLabel("Album: " + songAlbum);

        songPanel.add(songLabel);
        songPanel.add(artistLabel);
        songPanel.add(albumLabel);

        add(userPanel, BorderLayout.NORTH);
        add(albumPanel, BorderLayout.WEST);
        add(songPanel, BorderLayout.CENTER);
    }

}
