package views;

import interface_adapter.feed.FeedController;
import interface_adapter.profile.ProfileController;

import javax.swing.*;
import java.util.UUID;

public class BaseView extends JTabbedPane {
    private ProfileController profileController;
    private FeedController feedController;
    private UUID userID;
    private UUID promptID;
    public BaseView(SearchView searchView, ResponseView responseView,
                    FeedView feedPage, ProfileView profilePage) {
        addTab("Home", null);
        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    break;
                case 1:
                    feedController.execute(promptID);
                case 2:
                    profileController.execute(userID);
            }
        });
    }

}
