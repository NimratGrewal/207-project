package views;

import javax.swing.*;
import java.awt.*;

public class ProfileView extends JPanel {

    public ProfileView() {
        setLayout(new BorderLayout());

        // Create a panel for the additional content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Create a panel for the profile information
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        // Add profile picture placeholder, username, and number of responses
        JLabel profilePictureLabel = new JLabel("Profile Picture Placeholder");
        JLabel usernameLabel = new JLabel("Username: hayatariq");
        JLabel responsesLabel = new JLabel("Responses: 10");

        // Add some padding to create space around the profile info
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        profilePanel.add(profilePictureLabel);
        profilePanel.add(usernameLabel);
        profilePanel.add(responsesLabel);

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

        contentPanel.add(profilePanel, BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

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
            ProfileView profile = new ProfileView();
            profile.setVisible(true);
        });
    }
}
