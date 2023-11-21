package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    private SearchState state;
    public SearchViewModel() { super("search"); }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    public SearchState getState() {
        return state;
    }
}
