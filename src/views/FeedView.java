package views;

import entities.Response;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.UUID;

public class FeedView extends JPanel implements ActionListener, PropertyChangeListener {

    private final FeedViewModel viewModel;
    private final FeedController feedController;
    private final JLabel dateAndPromptLabel;
    private final JPanel responsesPanel;

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

        for (UUID response : viewModel.getState().getPromptResponses()) {
            JPanel responseBoxPanel = createFeedResponseBox(response);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10));
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
    private JPanel createFeedResponseBox(Response response) {
        return new views.FeedResponseBox(response);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setFields();
    }

    private void setFields() {
        dateAndPromptLabel.setText("Date: " + viewModel.getState().getPromptDate() +
                "</br> Prompt: " + viewModel.getState().getPromptText());

        responsesPanel.removeAll();

        for (UUID response : viewModel.getState().getPromptResponses()) {
            JPanel responseBoxPanel = createFeedResponseBox(response);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
        }

        responsesPanel.revalidate();
        responsesPanel.repaint();
    }
}
