package views;

import javax.swing.*;
import java.awt.*;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final ProfileViewModel viewModel;
    private final ProfileController profileController;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;

    public ProfileView(ProfileViewModel viewModel, ProfileController profileController) {
        this.viewModel = viewModel;
        this.profileController = profileController;

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

        for (Map.Entry<UUID, Map<String, Object>> entry : viewModel.getState().getResponseInfoMap().entrySet()) {
            UUID responseId = entry.getKey();
            Map<String, Object> responseInfo = entry.getValue();

            LocalDate promptDate = (LocalDate) responseInfo.get("Prompt Date");
            String promptText = (String) responseInfo.get("Prompt Text");

            JPanel responseBoxPanel = createProfileResponseBox(responseId, responseInfo, promptDate, promptText);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
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

    private JPanel createProfileResponseBox(UUID responseId, Map<String, Object> responseInfo,
                                            LocalDate promptDate, String promptText) {
        String username = (String) responseInfo.get("Username");
        String songName = (String) responseInfo.get("Song Name");
        String[] songArtists = ((String[]) responseInfo.get("Song Artists"));
        String songAlbum = (String) responseInfo.get("Song Album");
        ImageIcon albumArt = (ImageIcon) responseInfo.get("Album Art");

        return new ProfileResponseBox(responseId, username, songName, songArtists, songAlbum, albumArt, promptDate, promptText);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setFields();
    }

    private void setFields() {
        usernameLabel.setText("Username: " + viewModel.getState().getUsername());
        responsesLabel.setText("Responses: " + viewModel.getState().getNumberOfResponses());

        responsesPanel.removeAll();

        for (Map.Entry<UUID, Map<String, Object>> entry : viewModel.getState().getResponseInfoMap().entrySet()) {
            UUID responseId = entry.getKey();
            Map<String, Object> responseInfo = entry.getValue();

            // Retrieve additional information needed for ProfileResponseBox
            LocalDate promptDate = (LocalDate) responseInfo.get("Prompt Date");
            String promptText = (String) responseInfo.get("Prompt Text");

            // Create ProfileResponseBox with the additional information
            JPanel responseBoxPanel = createProfileResponseBox(responseId, responseInfo, promptDate, promptText);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
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
