package views;



import interface_adapter.search_users.SearchUsersController;
import interface_adapter.search_users.SearchUsersPresenter;
import interface_adapter.search_users.SearchUsersSearchBoxViewModel;
import interface_adapter.search_users.SearchUsersViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchUserView extends JPanel implements PropertyChangeListener {
    private final SearchUsersViewModel searchUsersViewModel;
    private final SearchUsersController searchUsersController;
    private final SearchUsersPresenter searchUsersPresenter;

    private final SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel;

    private final JTextField searchUserBar;
    private final JButton searchUserButton;

    private final JButton cancel;
    public SearchUserView(SearchUsersViewModel searchViewModel, SearchUsersController searchUsersController, SearchUsersPresenter searchUsersPresenter, SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel) {
        this.searchUsersViewModel = searchViewModel;
        this.searchUsersController = searchUsersController;
        this.searchUsersPresenter = searchUsersPresenter;
        this.searchUsersSearchBoxViewModel = searchUsersSearchBoxViewModel;
        this.searchUsersViewModel.addPropertyChangeListener(this);
        this.searchUsersSearchBoxViewModel.addPropertyChangeListener(this);

        searchUserBar = new JTextField();
        searchUserButton = new JButton();
        cancel = new JButton();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
