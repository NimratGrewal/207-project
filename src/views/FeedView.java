package views;

import data_access.PromptDataAccessObject;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.UUID;

public class FeedView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "feed";
    private final FeedViewModel viewModel;
    private final JLabel dateAndPromptLabel;
    private final JPanel responsesPanel;
    private final FeedController feedController;

    public FeedView(FeedViewModel viewModel, FeedController feedController) {
        this.viewModel = viewModel;
        this.feedController = feedController;

        viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        dateAndPromptLabel = new JLabel();

        // overall content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // header panel
        JPanel headerPanel = new JPanel(new BorderLayout());

        dateAndPromptLabel.setText("Date: " + viewModel.getState().getPromptDate() +
                "<br/> Prompt: " + viewModel.getState().getPromptText());

        headerPanel.add(dateAndPromptLabel, BorderLayout.NORTH);

        // spacing around text in header panel
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add header panel to content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // feed responses panel
        responsesPanel = new JPanel();
        responsesPanel.setLayout(new BoxLayout(responsesPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<UUID, Map<String, Object>> entry : viewModel.getState().getResponseInfoMap().entrySet()) {
            UUID userId = entry.getKey();
            Map<String, Object> responseInfo = entry.getValue();

            JPanel responseBoxPanel = createFeedResponseBox(responseInfo);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
        }

        // scroll pane for answers
        JScrollPane scrollPane = new JScrollPane(responsesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // space around answer boxes in scroll panel
        responsesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // spacing on page edges for content panel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(contentPanel, BorderLayout.CENTER);
    }
    private JPanel createFeedResponseBox(Map<String, Object> responseInfo) {
        UUID responseId = (UUID) responseInfo.get("Response ID");
        String username = (String) responseInfo.get("Username");
        String songName = (String) responseInfo.get("Song Name");
        String[] songArtists = ((String[]) responseInfo.get("Song Artists"));
        String songAlbum = (String) responseInfo.get("Song Album");
        ImageIcon albumArt = (ImageIcon) responseInfo.get("Album Art");

        return new FeedResponseBox(responseId, username, songName, songArtists, songAlbum, albumArt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FeedState state = (FeedState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(FeedState state) {
        dateAndPromptLabel.setText("Date: " + viewModel.getState().getPromptDate() +
                "</br> Prompt: " + viewModel.getState().getPromptText());

        responsesPanel.removeAll();

        for (Map.Entry<UUID, Map<String, Object>> entry : viewModel.getState().getResponseInfoMap().entrySet()) {
            UUID userId = entry.getKey();
            Map<String, Object> responseInfo = entry.getValue();

            JPanel responseBoxPanel = createFeedResponseBox(responseInfo);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
        }

        responsesPanel.revalidate();
        responsesPanel.repaint();
    }
}


