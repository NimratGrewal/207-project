package views;


import interface_adapter.search_users.SearchUsersController;
import interface_adapter.search_users.SearchUsersState;
import interface_adapter.search_users.SearchUsersViewModel;
import views.components.SearchedUserResponseBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SearchUserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "User Found";
    private final SearchUsersViewModel searchUsersViewModel;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;
    private final SearchUsersController searchUsersController;

    public SearchUserProfileView(SearchUsersViewModel searchUsersViewModel, SearchUsersController searchUsersController) {
        this.searchUsersViewModel = searchUsersViewModel;
        this.searchUsersController = searchUsersController;
        this.searchUsersViewModel.addPropertyChangeListener(this);
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

        URL resourceUrl = getClass().getResource("./assets/Portrait_placeholder.png");
        if (resourceUrl != null) {
            ImageIcon profilePictureIcon = new ImageIcon(resourceUrl);
            profilePictureLabel.setIcon(profilePictureIcon);
        } else {
            System.err.println("Portrait Placeholder Image Not Found!");
        }

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(d -> {
            if (d.getSource().equals(cancel)){
                this.searchUsersController.profileToSearch();
            }
        });

        profilePanel.add(profilePictureLabel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(usernameLabel);
        profilePanel.add(Box.createVerticalStrut(5));
        profilePanel.add(responsesLabel);
        profilePanel.add(Box.createVerticalGlue());
        profilePanel.add(cancel);

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

        SearchUsersState currentState = searchUsersViewModel.getState();
        searchUsersViewModel.setState(currentState);

        setFields(searchUsersViewModel.getState());
    }
    private void setFields(SearchUsersState state) {
        if (state == null) {
            System.out.println("profile state is null!");
            return;
        }

        usernameLabel.setText("Username: " + searchUsersViewModel.getState().getUsername());
        responsesLabel.setText("Number of Responses: " + searchUsersViewModel.getState().getNumberOfResponses());

        responsesPanel.removeAll();

        Map<UUID, Map<String, Object>> responseInfoMap = searchUsersViewModel.getState().getResponseInfoMap();
        if (!responseInfoMap.isEmpty()) {
            for (Map.Entry<UUID, Map<String, Object>> entry : searchUsersViewModel.getState().getResponseInfoMap().entrySet()) {
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

        return new SearchedUserResponseBox(responseId, songName, songArtists, songAlbum, albumArt, promptDate, promptText);
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchUsersState) {
            SearchUsersState state = (SearchUsersState) evt.getNewValue();
            setFields(state);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
