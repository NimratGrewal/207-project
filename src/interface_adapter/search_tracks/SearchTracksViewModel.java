package interface_adapter.search_tracks;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchTracksViewModel extends ViewModel {
    private SearchTracksState state = new SearchTracksState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public SearchTracksViewModel(String viewName) {
        super("search tracks");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchTracksState getState() {
        return this.state;
    }

    public void setState(SearchTracksState state) {
        this.state = state;
    }
}
