package views;

import javax.swing.*;
import java.util.UUID;

public class BaseView extends JTabbedPane {

    public BaseView(FeedView feedPage, ProfileView profilePage, UUID dailyPromptId, UUID loggedInUserId) {

        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);

        addChangeListener(e -> {
            int selectedIndex = getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    feedPage.executeFeedController(dailyPromptId);
                    break;
                case 1:
                    profilePage.executeProfileController(loggedInUserId);
                    break;
            }
        });
    }
}
