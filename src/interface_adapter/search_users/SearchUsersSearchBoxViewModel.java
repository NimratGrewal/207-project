package interface_adapter.search_users;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.search_tracks.SearchTracksState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUsersSearchBoxViewModel extends ViewModel {
    private SearchUsersSearchBoxState state = new SearchUsersSearchBoxState();
    public SearchUsersSearchBoxViewModel() {
        super("Search Users Search Box");
    }

    public final String BUTTON_LABEL ="SEARCH FOR USERS";
    public final String RETURN_LABEL = "RETURN";
    public void setState(SearchUsersSearchBoxState searchUsersSearchBoxState){
        this.state = searchUsersSearchBoxState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public SearchUsersSearchBoxState getState() {
        return state;
    }
}
