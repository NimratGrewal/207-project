package views;

import javax.swing.*;
import java.awt.*;

import entities.Response;
import interface_adapter.profile.ProfileState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final ProfileState profileState;
    private final JLabel usernameLabel;
    private final JLabel responsesLabel;
    private final JPanel responsesPanel;

    public ProfileView(ProfileState profileState, JLabel usernameLabel,
                       JLabel responsesLabel) {
        this.profileState = profileState;
        this.usernameLabel = usernameLabel;
        this.responsesLabel = responsesLabel;

        setLayout(new BorderLayout());

        // overall content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // profile info panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        JLabel profilePictureLabel = new JLabel("Profile Picture Placeholder");

        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        profilePanel.add(profilePictureLabel);
        profilePanel.add(usernameLabel);
        profilePanel.add(responsesLabel);

        // responses panel
        responsesPanel = new JPanel();
        responsesPanel.setLayout(new BoxLayout(responsesPanel, BoxLayout.Y_AXIS));

        // scroll pane for answers
        JScrollPane scrollPane = new JScrollPane(responsesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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

    private JPanel createResponseBox(Response response) {
        return new ResponseBox(response);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void updateUI(ProfileState updatedState) {
        usernameLabel.setText("Username: " + updatedState.getUsername());
        responsesLabel.setText("Responses: " + updatedState.getNumberOfResponses());

        // Clear the existing response panels before adding the updated ones
        responsesPanel.removeAll();

        // Iterate over the response history and add a ResponseBox for each response
        for (Response response : updatedState.getResponseHistory()) {
            JPanel responseBoxPanel = createResponseBox(response);
            responsesPanel.add(responseBoxPanel);
            responsesPanel.add(Box.createVerticalStrut(10)); // Add vertical space between response panels
        }

        // Revalidate and repaint the panel to reflect the changes
        responsesPanel.revalidate();
        responsesPanel.repaint();


    }
}

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        DeleteState state = (DeleteState) evt.getNewValue();
//        JOptionPane.showConfirmDialog(this, "Response" + state.getResponseId() + "was deleted!");
//    }
