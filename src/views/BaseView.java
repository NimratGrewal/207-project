package views;

import javax.swing.*;
import java.util.UUID;

public class BaseView extends JTabbedPane {

    public BaseView(SearchView searchView, ViewResponseView responseView, FeedView feedPage, ProfileView profilePage) {
        addTab("Home", null);
        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);
        addTab("Search User", null);

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    break;
                case 1:
                    feedPage.executeFeedController();
                    break;
                case 2:
                    profilePage.executeProfileController();
                    break;
                case 3:
                    break;

            }
        });
    }
}
