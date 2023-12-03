package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ResponseBox extends JPanel {
    protected static final int BOX_HEIGHT = 200;
    protected static final int TOP_PANEL_HEIGHT = 35;

    public ResponseBox(UUID responseId, String songName, List<String> songArtists,
                       String songAlbum, ImageIcon albumArt) {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, BOX_HEIGHT));

        JPanel songPanel = createSongPanel(songName, songArtists, songAlbum, albumArt);
        add(songPanel, BorderLayout.CENTER);
    }
    public void setTopPanel(JPanel topPanel) {
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 5, 5));
        topPanel.setPreferredSize(new Dimension(0, TOP_PANEL_HEIGHT));
        add(topPanel, BorderLayout.NORTH);
    }

    public JPanel createSongPanel(String songName, List<String> songArtists, String songAlbum, ImageIcon albumArt) {
        JPanel songPanel = new JPanel(new BorderLayout());

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

        songPanel.add(leftPanel, BorderLayout.WEST);
        songPanel.add(rightPanel, BorderLayout.CENTER);

        return songPanel;
    }
}
