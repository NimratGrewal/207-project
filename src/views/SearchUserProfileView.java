package views;


import interface_adapter.search_users.SearchUsersController;
import interface_adapter.search_users.SearchUsersViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class SearchUserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "User Found";
    private final SearchUsersViewModel searchUsersViewModel;
    private final SearchUsersController searchUsersController;
    public SearchUserProfileView(SearchUsersViewModel searchUsersViewModel, SearchUsersController searchUsersController) {
        this.searchUsersViewModel = searchUsersViewModel;
        this.searchUsersController = searchUsersController;
        this.searchUsersViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        JLabel usernameLabel = new JLabel();
        JLabel responsesLabel = new JLabel();
        JButton logout = new JButton("Log out");
        JButton cancel = new JButton(searchUsersViewModel.RETURN_LABEL);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        contentPanel.setBackground(Color.WHITE);

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        usernameLabel.setText("Username: " + searchUsersViewModel.getState().getUsername());
        responsesLabel.setText("Responses: " + searchUsersViewModel.getState().getNumberOfResponses());
        JLabel profilePictureLabel = new JLabel();

        ImageIcon profilePictureIcon = new ImageIcon(
                (Objects.requireNonNull(getClass().getResource("/views/assets/Portrait_placeholder.png"))));
        profilePictureLabel.setIcon(profilePictureIcon);

        profilePanel.add(profilePictureLabel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(usernameLabel);
        profilePanel.add(Box.createVerticalStrut(5));
        profilePanel.add(responsesLabel);
        profilePanel.add(Box.createVerticalGlue());
        profilePanel.add(logout);
        profilePanel.add(cancel);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUsersController.profile_to_search();
            }
        });

        // responses panel
        JPanel responsesPanel = new JPanel();
        responsesPanel.setLayout(new BoxLayout(responsesPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<UUID, Map<String, Object>> entry : searchUsersViewModel.getState().getResponseInfoMap().entrySet()) {
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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
