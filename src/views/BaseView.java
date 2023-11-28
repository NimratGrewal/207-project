package views;

import interface_adapter.feed.FeedController;
import interface_adapter.profile.ProfileController;

import javax.swing.*;
import java.util.UUID;

public class BaseView extends JTabbedPane {
    private ProfileController profileController;
    private FeedController feedController;
    public BaseView(SearchView searchView, ViewResponseView viewResponseView,
                    FeedView feedPage, ProfileView profilePage, UUID dailyPromptId, UUID loggedInUserId) {
        addTab("Home", null);
        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    break;
                case 1:
                    feedController.execute(dailyPromptId);
                case 2:
                    profileController.execute(loggedInUserId);
            }
        });
    }

}
