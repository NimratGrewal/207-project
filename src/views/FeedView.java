package views;

import use_case.FeedToProfile.FeedToProfileOutputBoundary;
import use_case.FeedToProfile.FeedToProfileInputBoundary;
import use_case.ProfileToFeed.ProfileToFeedInputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedView implements FeedToProfileOutputBoundary {
    private JFrame feedPage;
    private JButton ToProfile;
    private final FeedToProfileInputBoundary feedToProfileInputBoundary;

    public FeedView(FeedToProfileInputBoundary feedToProfileInputBoundary) {
        this.feedToProfileInputBoundary = feedToProfileInputBoundary;
        initialize();
    }

    private void initialize() {
        feedPage = new JFrame("Feed Page");
        ToProfile = new JButton("Profile");

        ToProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedToProfileInputBoundary.toProfile();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(ToProfile);

        feedPage.add(buttons);
    }

    public void show() {
        feedPage.setVisible(true);
    }

}
