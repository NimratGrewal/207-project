package views;

import javax.swing.*;
import java.awt.*;

import interface_adapter.delete.DeleteController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final DeleteController deleteController;

    public ProfileView(DeleteController deleteController) {
        this.deleteController = deleteController;

      
        setLayout(new BorderLayout());

        // overall content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // profile info panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        JLabel profilePictureLabel = new JLabel("Profile Picture Placeholder");
        JLabel usernameLabel = new JLabel("Username: hayatariq");
        JLabel responsesLabel = new JLabel("Responses: 10");

        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        profilePanel.add(profilePictureLabel);
        profilePanel.add(usernameLabel);
        profilePanel.add(responsesLabel);

        // answers panel
        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));

        // generating sample answers
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
      
        JPanel buttons = new JPanel();
        delete = new JButton(deleteViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)) {
                    DeleteState state = deleteViewModel.getState();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
                            "Are you sure you want to delete Response: " + state.getResponseId() + "?", "Warning", dialogButton);

                    if (dialogueResult == JOptionPane.YES_OPTION) {
                        DeleteState deleteState = deleteViewModel.getState();
                        deleteController.execute(deleteState.getResponseId());
                    }
                }
            }
        });
    }
  
    }
    private JPanel createAnswerPanel(String answerText) {
        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(Color.lightGray);

        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel answerLabel = new JLabel(answerText);
        answerPanel.add(answerLabel);

        return answerPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteState state = (DeleteState) evt.getNewValue();
        JOptionPane.showConfirmDialog(this, "Response" + state.getResponseId() + "was deleted!");
    }
