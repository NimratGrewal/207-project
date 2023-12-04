package interface_adapter.search_users;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUsersViewModel extends ViewModel {
    public SearchUsersState state = new SearchUsersState();
    public final String RETURN_LABEL = "RETURN";

    public SearchUsersViewModel() {
        super("search users profile");
    }

    public void setState(SearchUsersState searchUsersState){
        this.state = searchUsersState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        System.out.println("search users changed!");
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
