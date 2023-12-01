package interface_adapter.search_users;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUsersViewModel extends ViewModel {
    public SearchUsersState state = new SearchUsersState();

    public SearchUsersViewModel() {
        super("Search Users");
    }

    public void setState(SearchUsersState searchUsersState){
        this.state = searchUsersState;
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
    public SearchUsersState getState(){
        return state;
    }
}