package views;

import data_access.PromptDataAccessObject;
import entities.Prompt;
import entities.Response;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class ProfileResponseBox extends FeedResponseBox {
    public ProfileResponseBox(String username, String songName, String[] songArtists,
                              String songAlbum, ImageIcon albumArt, LocalDate promptDate, String promptText) {
        super(username, songName, songArtists, songAlbum, albumArt);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: " + promptDate);
        JLabel promptLabel = new JLabel("Prompt: " + promptText);
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
        topPanel.add(promptLabel, BorderLayout.NORTH);
        topPanel.remove(0);
    }
}
