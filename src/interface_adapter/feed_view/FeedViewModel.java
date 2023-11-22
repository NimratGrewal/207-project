package interface_adapter.feed_view;

import interface_adapter.ViewModel;
import interface_adapter.profile_view.ProfileState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FeedViewModel extends ViewModel {
    private ProfileState state;
    public FeedViewModel() { super("profile"); }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(ProfileState state) {
        this.state = state;
    }

    public ProfileState getState() {
        return state;
    }
}
