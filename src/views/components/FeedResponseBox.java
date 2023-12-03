package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class FeedResponseBox extends ResponseBox {
    public FeedResponseBox(UUID responseId, String songName, List<String> songArtists,
                           String songAlbum, ImageIcon albumArt, String username) {
        super(responseId, songName, songArtists, songAlbum, albumArt);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.setBackground(Color.GRAY);

        JLabel usernameLabel = new JLabel(username + " said");
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(usernameLabel, BoxLayout.X_AXIS);

        setTopPanel(topPanel);
    }

}


