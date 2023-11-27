package views;

import entities.Prompt;
import entities.Response;
import interface_adapter.profile.ProfileState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.UUID;

public class ProfileResponseBox extends FeedResponseBox{
    private final PromptDataAccessObject promptDataAccessObject;
    private ProfileResponseBoxListenerInterface listener;

    public void setProfileResponseBoxListener(ProfileResponseBoxListenerInterface listener) {
        this.listener = listener;
    }

    public ProfileResponseBox(Response response, PromptDataAccessObject promptDataAccessObject) {
        super(response);
        this.promptDataAccessObject = promptDataAccessObject;

        UUID promptID = response.getPromptId();
        Prompt prompt = promptDataAccessObject.getPromptById(promptID);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: " + prompt.getPromptDate());
        JLabel promptLabel = new JLabel("Prompt: " + prompt.getPromptText());
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
        topPanel.add(promptLabel, BorderLayout.NORTH);
        topPanel.remove(0);

        JButton delete = new JButton("delete");

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)) {
                    ProfileState state = listener.beforeDeleteAction(response.getResponseId());
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
                            "Are you sure you want to delete this response"+ state.getResponseId() + "?", "Warning", dialogButton);
                    if (dialogueResult == JOptionPane.YES_OPTION) {
                        listener.onDeleteAction(state); // delegate task to onDeleteAction method
            }
        }

        }});
    }

}
