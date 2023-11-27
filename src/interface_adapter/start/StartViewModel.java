package interface_adapter.start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class StartViewModel extends ViewModel {

    public StartViewModel() {
        super("Start View");
    }


    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
