package views;

import javax.swing.*;

public class BaseView extends JTabbedPane {
    public BaseView(FeedView feedPage, ProfileView profilePage) {
        addTab("Home", null);
        addTab("Feed", null, feedPage);
        addTab("Profile", null, profilePage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeedView pageOne = new FeedView();
            ProfileView pageTwo = new ProfileView();

            BaseView tabbedPage = new BaseView(pageOne, pageTwo);

            // Set up the main JFrame
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Add the tabbedPane to the JFrame
            frame.add(tabbedPage);

            frame.setVisible(true);
        });
    }

}
