package views;

import javax.swing.*;
import java.util.UUID;

public class BaseView extends JTabbedPane {

    public BaseView(FeedView feedPage, ProfileView profilePage) {

        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    feedPage.executeFeedController();
                    break;
                case 1:
                    profilePage.executeProfileController();
                    break;
            }
        });
    }
}
