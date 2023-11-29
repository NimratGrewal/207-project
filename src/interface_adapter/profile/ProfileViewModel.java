package interface_adapter.profile;

import interface_adapter.ViewModel;
import interface_adapter.feed.FeedViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private ProfileState state = new ProfileState();
    public ProfileViewModel() {
        super("profile");
    }

    public void setState(ProfileState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ProfileState getState() {
        return state;
    }
}


