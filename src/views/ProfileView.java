package views;

import javax.swing.*;
import java.awt.*;

import data_access.FileUserDataAccessObject;
import entities.Response;
import interface_adapter.delete.DeleteController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import java.util.UUID;

public class ProfileView extends JPanel implements ProfileResponseBoxListenerInterface, PropertyChangeListener {
    private final ProfileViewModel viewModel;
    private final ProfileController profileController;
    private final DeleteController deleteController;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;
    private final FileUserDataAccessObject fileUserDataAccessObject;

    public ProfileView(ProfileViewModel viewModel, ProfileController profileController,
                       DeleteController deleteController, FileUserDataAccessObject fileUserDataAccessObject) {
        this.viewModel = viewModel;
        this.profileController = profileController;
        this.deleteController = deleteController;
        this.fileUserDataAccessObject = fileUserDataAccessObject;

        viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        usernameLabel = new JLabel();
        responsesLabel = new JLabel();

        // overall content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        contentPanel.setBackground(Color.WHITE);

        // profile info panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        usernameLabel.setText("Username: " + viewModel.getState().getUsername());
        responsesLabel.setText("Responses: " + viewModel.getState().getNumberOfResponses());

        JLabel profilePictureLabel = new JLabel();

        ImageIcon profilePictureIcon = new ImageIcon(
                (Objects.requireNonNull(getClass().getResource("/views/assets/Portrait_placeholder.png"))));
        profilePictureLabel.setIcon(profilePictureIcon);

        JButton logout = new JButton("Log out");

        profilePanel.add(profilePictureLabel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(usernameLabel);
        profilePanel.add(Box.createVerticalStrut(5));
        profilePanel.add(responsesLabel);
        profilePanel.add(Box.createVerticalGlue());
        profilePanel.add(logout);

        // responses panel
        responsesPanel = new JPanel();
        responsesPanel.setLayout(new BoxLayout(responsesPanel, BoxLayout.Y_AXIS));

        for (UUID responseId : viewModel.getState().getResponseIds()) {
            Response response = getResponseById(responseId);
            if (response != null) {
                JPanel responseBoxPanel = createProfileResponseBox(response);
                responsesPanel.add(responseBoxPanel);
                responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
            }
        }

        // scroll pane for answers
        JScrollPane scrollPane = new JScrollPane(responsesPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(Color.WHITE);

        // space around answer boxes in scroll panel
        responsesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        contentPanel.add(profilePanel, BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
      
        JPanel buttons = new JPanel();

        }

//        delete = new JButton(ProfileViewModel.DELETE_BUTTON_LABEL);
//        buttons.add(delete);
//
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(delete)) {
//                    ProfileState state = viewModel.getState();
//                    state.setResponseId();
//                    int dialogButton = JOptionPane.YES_NO_OPTION;
//                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
//                            "Are you sure you want to delete Response: " + state.getResponseId() + "?", "Warning", dialogButton);
//
//                    if (dialogueResult == JOptionPane.YES_OPTION) {
//                        ProfileState profilestate = viewModel.getState();
//                        ProfileView.this.deleteController.execute(profilestate.getResponseId());
//                    }
//                }
//            }
//        });

    }

    private Response getResponseById(UUID responseId) {
        return fileUserDataAccessObject.getResponseById(viewModel.getState().getUserID(), responseId);
    }
    private JPanel createProfileResponseBox(Response response) {
        ProfileResponseBox responseBox = new ProfileResponseBox(response, promptDataAccessObject);
        responseBox.setProfileResponseBoxListener(this); // Set the listener
        return responseBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setFields();
    }

    private void setFields() {
        usernameLabel.setText("Username: " + viewModel.getState().getUsername());
        responsesLabel.setText("Responses: " + viewModel.getState().getNumberOfResponses());

        responsesPanel.removeAll();

        for (UUID responseId : viewModel.getState().getResponseIds()) {
            Response response = getResponseById(responseId);
            if (response != null) {
                JPanel responseBoxPanel = createProfileResponseBox(response);
                responsesPanel.add(responseBoxPanel);
                responsesPanel.add(Box.createVerticalStrut(10));
            }
        }

        responsesPanel.revalidate();
        responsesPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onDeleteAction(ProfileState state) {
        deleteController.execute(state.getResponseId());
    }

    @Override
    public ProfileState beforeDeleteAction(UUID responseId) {
        ProfileState state = viewModel.getState();
        state.setResponseId(responseId);
        viewModel.setState(state);
        return state;
    }
}



