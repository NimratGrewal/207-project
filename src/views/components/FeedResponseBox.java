package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.UUID;


public class FeedResponseBox extends JPanel {
    public FeedResponseBox(UUID responseId, String username, String songName, String[] songArtists, String songAlbum, ImageIcon albumArt) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.setBackground(Color.GRAY);

        JLabel usernameLabel = new JLabel(username + " said");
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(usernameLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel albumCoverLabel = new JLabel(albumArt);
        leftPanel.add(albumCoverLabel, BorderLayout.CENTER);
        leftPanel.setBackground(Color.lightGray);

        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        rightPanel.setBackground(Color.lightGray);

        JLabel songLabel = new JLabel("Song: " + songName);
        JLabel artistLabel = new JLabel("Artist: " + Arrays.toString(songArtists));
        JLabel albumLabel = new JLabel("Album: " + songAlbum);

        rightPanel.add(songLabel);
        rightPanel.add(artistLabel);
        rightPanel.add(albumLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

}


