package Views;

import javax.swing.*;
import java.awt.*;

public class FeedResponseBox extends JPanel {
    public FeedResponseBox(String username, String songName, String artist, String albumName, String genre) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel usernameLabel = new JLabel(username + " said");
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(usernameLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel albumCoverLabel = new JLabel("Album Cover"); // Replace with your actual image or component
        leftPanel.add(albumCoverLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel songLabel = new JLabel("Song: " + songName);
        JLabel artistLabel = new JLabel("Artist: " + artist);
        JLabel albumLabel = new JLabel("Album: " + albumName);
        JLabel genreLabel = new JLabel("Genre: " + genre);

        rightPanel.add(songLabel);
        rightPanel.add(artistLabel);
        rightPanel.add(albumLabel);
        rightPanel.add(genreLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

}


