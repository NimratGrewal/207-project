package interface_adapter.feed;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FeedViewModel extends ViewModel {
    private FeedState state = new FeedState();
    private FeedViewModel() {
        super("feed");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(FeedState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FeedState getState() {
        return state;
    }
}
