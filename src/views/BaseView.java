package views;

import javax.swing.*;

public class BaseView extends JFrame {
    private JTabbedPane tabbedPane;
    public BaseView() {
        tabbedPane = new JTabbedPane();

        FeedView feedPage = new FeedView();
        ProfileView profilePage = new ProfileView();

        tabbedPane.addTab("Home", null);
        tabbedPane.addTab("Feed", null, feedPage);
        tabbedPane.addTab("Profile", null, profilePage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BaseView base = new BaseView();
            base.setVisible(true);
        });
    }

}
