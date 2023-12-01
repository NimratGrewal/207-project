package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

public class ProfileResponseBox extends FeedResponseBox {
        JButton delete = new JButton("delete");

        public ProfileResponseBox(UUID responseId, String username, String songName, List<String> songArtists,
                                  String songAlbum, ImageIcon albumArt, LocalDate promptDate, String promptText) {
            super(responseId, username, songName, songArtists, songAlbum, albumArt);

            JPanel topPanel = (JPanel) getComponent(0);
            topPanel.removeAll();

            JLabel dateLabel = new JLabel("Date: " + promptDate);
            JLabel promptLabel = new JLabel("Prompt: " + promptText);
            dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
            topPanel.add(dateLabel, BorderLayout.NORTH);
            topPanel.add(promptLabel, BorderLayout.NORTH);

            JPanel deletePanel = new JPanel(new BorderLayout());
            deletePanel.add(delete, BorderLayout.CENTER);

            add(deletePanel, BorderLayout.EAST);

            delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
                            "Are you sure you want to delete this response" + responseId + "?", "Warning", dialogButton);
                    if (dialogueResult == JOptionPane.YES_OPTION) {
                        firePropertyChange("deleteResponse", null, responseId);
                    }
                }
            }
        });
    }

    public void addDeleteButtonListener(PropertyChangeListener listener) {
        delete.addPropertyChangeListener(listener);
    }

}



