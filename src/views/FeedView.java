package views;

import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import views.components.FeedResponseBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "feed";
    private final FeedViewModel viewModel;
    private final JLabel dateLabel;
    private final JLabel promptLabel;
    private final JPanel responsesPanel;

    public FeedView(FeedViewModel viewModel) {
        this.viewModel = viewModel;

        viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        dateLabel = new JLabel();
        promptLabel = new JLabel();

        // overall content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));

        headerPanel.add(promptLabel, BoxLayout.X_AXIS);
        headerPanel.add(dateLabel, BoxLayout.X_AXIS);

        // spacing around text in header panel
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add header panel to content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // feed responses panel
        responsesPanel = new JPanel();
        responsesPanel.setLayout(new BoxLayout(responsesPanel, BoxLayout.Y_AXIS));

        // scroll pane for answers
        JScrollPane scrollPane = new JScrollPane(responsesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // space around answer boxes in scroll panel
        responsesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // spacing on page edges for content panel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(contentPanel, BorderLayout.CENTER);

        FeedState currentState = viewModel.getState();
        viewModel.setState(currentState);

        setFields(viewModel.getState());
    }

    public void setFields(FeedState state) {
        if (state == null) {
            System.out.println("feed state is null!");
            return;
        }

        dateLabel.setText(viewModel.PROMPT_DATE_LABEL);
        promptLabel.setText(viewModel.PROMPT_TEXT_LABEL);

        responsesPanel.removeAll();

        Map<UUID, Map<String, Object>> responseInfoMap = state.getResponseInfoMap();
        if (!responseInfoMap.isEmpty()) {
            for (Map.Entry<UUID, Map<String, Object>> entry : responseInfoMap.entrySet()) {
                UUID userId = entry.getKey();
                Map<String, Object> responseInfo = entry.getValue();

                JPanel responseBoxPanel = createFeedResponseBox(responseInfo);

                responsesPanel.add(responseBoxPanel);
                responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
            }
        } else {
            JLabel no_responses = new JLabel("No Responses Yet!");
            responsesPanel.add(no_responses, BorderLayout.NORTH);
        }

        responsesPanel.revalidate();
        responsesPanel.repaint();
    }
    private JPanel createFeedResponseBox(Map<String, Object> responseInfo) {
        UUID responseId = (UUID) responseInfo.get("Response ID");
        String username = (String) responseInfo.get("Username");
        String songName = (String) responseInfo.get("Song Name");
        java.util.List<String> songArtists = (List<String>) responseInfo.get("Song Artists");
        String songAlbum = (String) responseInfo.get("Song Album");
        ImageIcon albumArt = (ImageIcon) responseInfo.get("Album Art");

        return new FeedResponseBox(responseId, songName, songArtists, songAlbum, albumArt, username);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof FeedState state) {
            state = (FeedState) evt.getNewValue();
            setFields(state);
        }
    }
}


