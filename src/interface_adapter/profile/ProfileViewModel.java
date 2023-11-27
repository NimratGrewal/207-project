package interface_adapter.profile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel {
    private ProfileState state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(ProfileState state) {
        ProfileState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public ProfileState getState() {
        return state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}


