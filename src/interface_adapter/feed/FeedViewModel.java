package interface_adapter.feed;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FeedViewModel {
    private FeedState state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(FeedState state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FeedState getState() {
        return state;
    }
}
