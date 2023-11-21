package interface_adapter.delete;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteViewModel extends ViewModel {

    public final String TITLE_LABEL = "Delete View";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    private DeleteState state = new DeleteState();
    public DeleteViewModel() {
        super("delete");
    }
    public void setState(DeleteState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public DeleteState getState() {
        return state;
    }


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
