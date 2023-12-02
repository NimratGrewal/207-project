package views;

import javax.swing.*;
import java.awt.*;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.delete.DeleteController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.List;

public class ProfileView extends JPanel implements PropertyChangeListener {
    public final String viewName = "profile";
    private final ProfileViewModel viewModel;
    private final ProfileController profileController;
    private final DeleteController deleteController;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;


    public ProfileView(ProfileViewModel viewModel,
                       ProfileController profileController,
                       DeleteController deleteController) {
        this.viewModel = viewModel;
        this.profileController = profileController;
        this.deleteController = deleteController;

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

        JLabel profilePictureLabel = new JLabel();

        URL resourceUrl = getClass().getResource("/images/Portrait_placeholder.png");
        if (resourceUrl != null) {
            ImageIcon profilePictureIcon = new ImageIcon(resourceUrl);
            profilePictureLabel.setIcon(profilePictureIcon);
        } else {
            System.err.println("Portrait Placeholder Image Not Found!");
        }

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

        ProfileState currentState = viewModel.getState();
        viewModel.setState(currentState);

        setFields(viewModel.getState());
    }

    public void executeProfileController() {
        profileController.execute();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    private void setFields(ProfileState state) {
        if (state == null) {
            System.out.println("state is null!");
            return;
        }

        usernameLabel.setText(viewModel.USERNAME_LABEL);
        responsesLabel.setText(viewModel.RESPONSES_LABEL);

        responsesPanel.removeAll();

        Map<UUID, Map<String, Object>> responseInfoMap = viewModel.getState().getResponseInfoMap();
        if (!responseInfoMap.isEmpty()) {
            for (Map.Entry<UUID, Map<String, Object>> entry : viewModel.getState().getResponseInfoMap().entrySet()) {
                UUID responseId = entry.getKey();
                Map<String, Object> responseInfo = entry.getValue();

                LocalDate promptDate = (LocalDate) responseInfo.get("Prompt Date");
                String promptText = (String) responseInfo.get("Prompt Text");

                JPanel responseBoxPanel = createProfileResponseBox(responseId, responseInfo, promptDate, promptText);

                responsesPanel.add(responseBoxPanel);
                responsesPanel.add(Box.createVerticalStrut(10));
            }
        } else {
            JLabel no_responses = new JLabel("No Responses Yet!");
            responsesPanel.add(no_responses, BorderLayout.NORTH);
        }

        responsesPanel.revalidate();
        responsesPanel.repaint();
    }

    private JPanel createProfileResponseBox(UUID responseId, Map<String, Object> responseInfo,
                                            LocalDate promptDate, String promptText) {
        String username = (String) responseInfo.get("Username");
        String songName = (String) responseInfo.get("Song Name");
        List<String> songArtists = (List<String>) responseInfo.get("Song Artists");
        String songAlbum = (String) responseInfo.get("Song Album");
        ImageIcon albumArt = (ImageIcon) responseInfo.get("Album Art");

        return new ProfileResponseBox(responseId, songName, songArtists, songAlbum, albumArt, promptDate, promptText);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        ProfileState state = (ProfileState) evt.getNewValue();
        setFields(state);

        if ("deleteResponse".equals(evt.getPropertyName())) {
            UUID responseId = (UUID) evt.getNewValue();
            // Pass responseId to delete controller
            deleteController.execute(responseId);
        }

    }





