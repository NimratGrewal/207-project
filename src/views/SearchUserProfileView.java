package views;

import interface_adapter.search_users.SearchUsersViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchUserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "User Found";
    private final SearchUsersViewModel searchUsersViewModel;

    public SearchUserProfileView(SearchUsersViewModel searchUsersViewModel) {
        this.searchUsersViewModel = searchUsersViewModel;
        this.searchUsersViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
