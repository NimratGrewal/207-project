package views;

import javax.swing.*;
import java.awt.*;

public class ProfileResponseBox extends Views.FeedResponseBox {
    public ProfileResponseBox(String username, String songName, String artist, String albumName, String genre, String date) {
        super(username, songName, artist, albumName, genre);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: " + date);
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
    }
}
