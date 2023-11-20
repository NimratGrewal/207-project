package views;

import use_case.ProfileToFeed.ProfileToFeedOutputBoundary;
import use_case.ProfileToFeed.ProfileToFeedInputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView implements ProfileToFeedInputBoundary {
    private JFrame profilePage;
    private JButton ToFeed;
    private final ProfileToFeedInputBoundary profileToFeedInputBoundary;
    private final ProfileToFeedOutputBoundary profileToFeedOutputBoundary;

    public ProfileView(ProfileToFeedInputBoundary profileToFeedInputBoundary,
                       ProfileToFeedOutputBoundary profileToFeedOutputBoundary) {
        this.profileToFeedInputBoundary = profileToFeedInputBoundary;
        this.profileToFeedOutputBoundary = profileToFeedOutputBoundary;
        initialize();
    }

    private void initialize() {
        profilePage = new JFrame("Profile Page");
        ToFeed = new JButton("Feed");

        ToFeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileToFeedInputBoundary.feedClicked();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(ToFeed);

        profilePage.add(buttons);
    }

    public void show() {
        profilePage.setVisible(true);
    }

    @Override
    public void feedClicked() {
        profileToFeedOutputBoundary.toFeed();
    }
}
