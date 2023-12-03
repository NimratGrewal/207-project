package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private final String username;
    private final int numberOfResponses;
    private ProfileState state = new ProfileState();

    public String USERNAME_LABEL = "Username: ";
    public String RESPONSES_LABEL = "Number of Responses: ";
    public ProfileViewModel(String username, int numberOfResponses) {
        super("profile");
        this.username = username;
        this.numberOfResponses = numberOfResponses;
        initializeState();
        setLabels();
    }

    private void initializeState() {
        state.setUsername(username);
        state.setNumberOfResponses(numberOfResponses);
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void setState(ProfileState state) {
        this.state = state;
        setLabels();
        firePropertyChanged();
    }
    private void setLabels() {
        USERNAME_LABEL = "Username: " + username;
        RESPONSES_LABEL = "Number Of Responses: " + numberOfResponses;
    }
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


