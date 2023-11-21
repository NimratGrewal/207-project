package interface_adapter.view_response;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewResponseViewModel extends ViewModel {
    private ViewResponseState state;
    public ViewResponseViewModel() { super("view response"); }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(ViewResponseState state) {
        this.state = state;
    }

    public ViewResponseState getState() {
        return state;
    }
}
