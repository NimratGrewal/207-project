package views;

import data_access.PromptDataAccessObject;
import entities.Prompt;
import entities.Response;
import interface_adapter.profile.ProfileState;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;
import java.beans.PropertyChangeSupport;


public class ProfileResponseBox extends FeedResponseBox {
    public ProfileResponseBox(UUID responseId, String username, String songName, String[] songArtists,
                              String songAlbum, ImageIcon albumArt, LocalDate promptDate, String promptText) {
        super(responseId, username, songName, songArtists, songAlbum, albumArt);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: " + promptDate);
        JLabel promptLabel = new JLabel("Prompt: " + promptText);
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
        topPanel.add(promptLabel, BorderLayout.NORTH);
        topPanel.remove(0);


        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)) {
//                    ProfileState state = listener.beforeDeleteAction(response.getResponseId());
//                    int dialogButton = JOptionPane.YES_NO_OPTION;
//                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
//                            "Are you sure you want to delete this response"+ state.getResponseId() + "?", "Warning", dialogButton);
//                    if (dialogueResult == JOptionPane.YES_OPTION) {
//                        listener.onDeleteAction(state); // delegate task to onDeleteAction method
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
                            "Are you sure you want to delete this response" + response.getResponseId() + "?", "Warning", dialogButton);
                    if (dialogueResult == JOptionPane.YES_OPTION) {
                        firePropertyChange("deleteResponse", null, response.getResponseId());
                    }
                }

            }
        });

    }

    public void addDeleteButtonListener(PropertyChangeListener listener) {
        delete.addPropertyChangeListener(listener);
    }

    // public void addPropertyChangeListener(PropertyChangeListener listener) {
    //     support.addPropertyChangeListener(listener);
    //}

}



