package views;

import interface_adapter.feed.FeedController;
import interface_adapter.profile.ProfileController;
import interface_adapter.prompt.PromptController;
import interface_adapter.search_users.SearchUsersController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BaseView extends JTabbedPane {
    private ProfileController profileController;
    private FeedController feedController;
    private PromptController promptController;
    private SearchUsersController searchUsersController;

    public BaseView(ProfileController profileController, FeedController feedController, PromptController promptController, SearchUsersController searchUsersController) {
        this.profileController = profileController;
        this.feedController = feedController;
        this.promptController = promptController;
        this.searchUsersController = searchUsersController;

        System.out.println("change listener added");
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof BaseView b) {
                    int selectedIndex = b.getSelectedIndex();
                    System.out.println(selectedIndex + "clicked!");
                    switch (selectedIndex) {
                        case 0:
                            System.out.println("starting prompt");
                            promptController.execute();
                            break;
                        case 1:
                            System.out.println("starting feed");
                            feedController.execute();
                            break;
                        case 2:
                            System.out.println("starting profile");
                            profileController.execute();
                            break;
                        case 3:
                            System.out.println("starting search users");
                            searchUsersController.profileToSearch();
                            break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
