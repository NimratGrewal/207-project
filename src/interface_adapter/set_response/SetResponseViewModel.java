package interface_adapter.set_response;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class SetResponseViewModel extends ViewModel {
    public SetResponseViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
