package views;

import interface_adapter.feed.FeedController;
import interface_adapter.profile.ProfileController;
import interface_adapter.prompt.PromptController;

import javax.swing.*;

public class BaseView extends JTabbedPane {
    private ProfileController profileController;
    private FeedController feedController;
    private PromptController promptController;
    public BaseView(ProfileController profileController, FeedController feedController, PromptController promptController) {
        this.profileController = profileController;
        this.feedController = feedController;
        this.promptController = promptController;

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    this.promptController.execute();
                case 1:
                    this.feedController.execute();
                case 2:
                    this.profileController.execute();
                case 3:
                    break;

            }
        });
    }
}
