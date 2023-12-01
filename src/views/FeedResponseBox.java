package views;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class FeedResponseBox extends JPanel {
    private static final int BOX_HEIGHT = 200;
    private static final int TOP_PANEL_HEIGHT = 35;
    public FeedResponseBox(UUID responseId, String username, String songName,
                           List<String> songArtists, String songAlbum, ImageIcon albumArt) {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, BOX_HEIGHT));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 5, 5));
        topPanel.setBackground(Color.GRAY);
        topPanel.setPreferredSize(new Dimension(0, TOP_PANEL_HEIGHT));

        JLabel usernameLabel = new JLabel(username + " said");
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(usernameLabel, BoxLayout.X_AXIS);

        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        JLabel albumCoverLabel = new JLabel();
        albumCoverLabel.setIcon(albumArt);
        leftPanel.add(albumCoverLabel, BorderLayout.CENTER);
        leftPanel.setBackground(Color.lightGray);

        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        rightPanel.setBackground(Color.lightGray);

        String songArtistsString = String.join(", ", songArtists);

        JLabel songLabel = new JLabel("Song: " + songName);
        JLabel artistLabel = new JLabel("Artist: " + songArtistsString);
        JLabel albumLabel = new JLabel("Album: " + songAlbum);

        rightPanel.add(songLabel);
        rightPanel.add(artistLabel);
        rightPanel.add(albumLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        revalidate();
    }

}


