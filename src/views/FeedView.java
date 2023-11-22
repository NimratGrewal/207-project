package views;

import javax.swing.*;
import java.awt.*;

public class FeedView extends JPanel {

    public FeedView() {
        setLayout(new BorderLayout());

        // Create a panel for the additional content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel dateAndPromptLabel = new JLabel("<html>Month Day, Year<br/>" +
                "What is your favorite song to sing in the shower?</html>");
        headerPanel.add(dateAndPromptLabel, BorderLayout.NORTH);

        // spacing around text in header panel
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add header panel to content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // answers panel
        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));

        // Add sample answers with outlined boxes and horizontal spacing
        for (int i = 1; i <= 20; i++) {
            answersPanel.add(createAnswerPanel("Answer " + i));
            answersPanel.add(Box.createVerticalStrut(10)); // Add vertical space between answers
        }

        // scroll pane for answers
        JScrollPane scrollPane = new JScrollPane(answersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // space around answer boxes in scroll panel
        answersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // spacing on page edges for content panel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Add the content panel to the center of the page
        add(contentPanel, BorderLayout.CENTER);
    }
    private JPanel createAnswerPanel(String answerText) {
        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(Color.lightGray);

        // Add margins to create horizontal spacing
        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel answerLabel = new JLabel(answerText);
        answerPanel.add(answerLabel);

        return answerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeedView feed = new FeedView();
            feed.setVisible(true);
        });
    }
}
