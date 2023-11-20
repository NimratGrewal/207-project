package interface_adapter.profile;

import interface_adapter.ViewModel;
import interface_adapter.feed.FeedState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    public final String TITLE_LABEL = "Profile page";

    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String FEED_BUTTON_LABEL = "Feed";

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

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ProfileState getState() {
        return state;
    }
}
