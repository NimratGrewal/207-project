package views;



import interface_adapter.search_users.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchUserView extends JPanel implements PropertyChangeListener {
    private final SearchUsersController searchUsersController;
    private final SearchUsersPresenter searchUsersPresenter;

    private final SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel;

    private final JTextField searchUserBar;
    private final JButton searchUserButton;

    public SearchUserView(SearchUsersController searchUsersController, SearchUsersPresenter searchUsersPresenter, SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel) {
        this.searchUsersController = searchUsersController;
        this.searchUsersPresenter = searchUsersPresenter;
        this.searchUsersSearchBoxViewModel = searchUsersSearchBoxViewModel;
        this.searchUsersSearchBoxViewModel.addPropertyChangeListener(this);

        searchUserBar = new JTextField();
        searchUserButton = new JButton(searchUsersSearchBoxViewModel.BUTTON_LABEL);

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchUserButton)){
                    SearchUsersSearchBoxState currentState = searchUsersSearchBoxViewModel.getState();
                    searchUsersController.execute(currentState.getUsername());
                }
            }
        });
        searchUserBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchUsersSearchBoxState currentState = searchUsersSearchBoxViewModel.getState();
                currentState.setUsername(searchUserBar.getText() + e.getKeyChar());
                searchUsersSearchBoxViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(searchUserBar);
        this.add(searchUserButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
