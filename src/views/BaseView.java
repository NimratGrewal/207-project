package views;

import javax.swing.*;

public class BaseView extends JTabbedPane {
    public BaseView(SearchView searchView, ResponseView responseView,
                    FeedView feedPage, ProfileView profilePage) {
        addTab("Home", null);
        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);

//        addChangeListener(e -> {
//            int selectedIndex = getSelectedIndex();
//            if (selectedIndex == 0) {
//                if (user.getResponse(promptId) != null) {
//                    setComponentAt(0, responseView);
//                } else {
//                    setComponentAt(0, searchView);
//                }
//            }
//        });
    }

}
