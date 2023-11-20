package interface_adapter.feed;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FeedViewModel extends ViewModel {
    public final String TITLE_LABEL = "Feed-page";

    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String PROFILE_BUTTON_LABEL = "Profile";

    private FeedState state = new FeedState();

    public FeedViewModel() {
        super("feed");
    }

    public void setState(FeedState state) {
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

    public FeedState getState() {
        return state;
    }
}
