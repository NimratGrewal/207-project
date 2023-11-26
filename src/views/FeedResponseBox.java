package views;

import entities.Response;

import javax.swing.*;
import java.awt.*;

public class FeedResponseBox extends JPanel {
    public FeedResponseBox(Response response) {
        String username = response.getUser().getUsername();
        String songName = response.getSong().getName();
//        List<String> songArtists = response.getSong().getArtists();
        String songAlbum = response.getSong().getAlbum();
        String albumCoverUrl = response.getSong().getAlbumCoverUrl();

        ImageIcon albumCover = new ImageIcon(albumCoverUrl);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel usernameLabel = new JLabel(username + " said");
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(usernameLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel albumCoverLabel = new JLabel("Album Cover");
        leftPanel.add(albumCoverLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel songLabel = new JLabel("Song: " + songName);
//        JLabel artistLabel = new JLabel("Artist: " + songArtists);
        JLabel albumLabel = new JLabel("Album: " + songAlbum);

        rightPanel.add(songLabel);
//        rightPanel.add(artistLabel);
        rightPanel.add(albumLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

}


