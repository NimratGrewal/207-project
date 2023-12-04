package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private ProfileState state = new ProfileState();

    public final String TITLE_LABEL = "Profile View";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ProfileViewModel() {
        super("profile");
    }

    public void setState(ProfileState state) {
        this.state = state;
    }

    public ProfileState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}


