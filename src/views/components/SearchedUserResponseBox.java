package views.components;

import views.components.ResponseBox;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class SearchedUserResponseBox extends ResponseBox {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchedUserResponseBox(UUID responseId, String songName, List<String> songArtists,
                                   String songAlbum, ImageIcon albumArt, LocalDate promptDate, String promptText) {
        super(responseId, songName, songArtists, songAlbum, albumArt);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GRAY);

        JLabel dateLabel = new JLabel("Date: " + promptDate);
        JLabel promptLabel = new JLabel("Prompt: " + promptText);

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.add(dateLabel);
        topPanel.add(promptLabel);

        setTopPanel(topPanel);



    }

    public SearchedUserResponseBox(UUID responseId, String songName, List<String> songArtists, String songAlbum, ImageIcon albumArt) {
        super(responseId, songName, songArtists, songAlbum, albumArt);
    }
}



