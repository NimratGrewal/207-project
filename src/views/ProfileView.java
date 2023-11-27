package views;

import javax.swing.*;
import java.awt.*;

import data_access.FileUserDataAccessObject;
import entities.Response;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import java.util.UUID;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final ProfileViewModel viewModel;
    private final ProfileController profileController;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;
    private final FileUserDataAccessObject fileUserDataAccessObject;

    public ProfileView(ProfileViewModel viewModel, ProfileController profileController,
                       FileUserDataAccessObject fileUserDataAccessObject) {
        this.viewModel = viewModel;
        this.profileController = profileController;
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
                (Objects.requireNonNull(getClass().getResource("/images/Portrait_placeholder.png"))));
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
      
//        JPanel buttons = new JPanel();
//        delete = new JButton(deleteViewModel.DELETE_BUTTON_LABEL);
//        buttons.add(delete);
//
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(delete)) {
//                    DeleteState state = deleteViewModel.getState();
//                    int dialogButton = JOptionPane.YES_NO_OPTION;
//                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
//                            "Are you sure you want to delete Response: " + state.getResponseId() + "?", "Warning", dialogButton);
//
//                    if (dialogueResult == JOptionPane.YES_OPTION) {
//                        DeleteState deleteState = deleteViewModel.getState();
//                        deleteController.execute(deleteState.getResponseId());
//                    }
//                }
//            }
//        });
    }

    private Response getResponseById(UUID responseId) {
        return fileUserDataAccessObject.getResponseById(viewModel.getState().getUserID(), responseId);
    }
    private JPanel createProfileResponseBox(Response response) {
        return new views.ProfileResponseBox(response);
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
}

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        DeleteState state = (DeleteState) evt.getNewValue();
//        JOptionPane.showConfirmDialog(this, "Response" + state.getResponseId() + "was deleted!");
//    }
