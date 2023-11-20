package views;

import use_case.FeedToProfile.FeedToProfileOutputBoundary;
import use_case.FeedToProfile.FeedToProfileInputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedView implements FeedToProfileInputBoundary {
    private JFrame feedPage;
    private JButton ToProfile;
    private final FeedToProfileInputBoundary feedToProfileInputBoundary;
    private final FeedToProfileOutputBoundary feedToProfileOutputBoundary;

    public FeedView(FeedToProfileInputBoundary feedToProfileInputBoundary,
                    FeedToProfileOutputBoundary feedToProfileOutputBoundary) {
        this.feedToProfileInputBoundary = feedToProfileInputBoundary;
        this.feedToProfileOutputBoundary = feedToProfileOutputBoundary;
        initialize();
    }

    private void initialize() {
        feedPage = new JFrame("Feed Page");
        ToProfile = new JButton("Profile");

        ToProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedToProfileInputBoundary.profileClicked();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(ToProfile);

        feedPage.add(buttons);
    }

    public void show() {
        feedPage.setVisible(true);
    }

    @Override
    public void profileClicked() {
        feedToProfileOutputBoundary.toProfile();
    }
}
