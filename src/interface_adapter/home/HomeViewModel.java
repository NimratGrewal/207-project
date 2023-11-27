package interface_adapter.home;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {

    public final String TITLE_LABEL = "Home View";

    private HomeState state = new HomeState();

    public HomeViewModel() {
        super("home");
    }
    public void setState(HomeState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomeState getState() {return this.state;}
}
