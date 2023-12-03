package interface_adapter.feed;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

public class FeedViewModel extends ViewModel {
    private final LocalDate promptDate;
    private final String promptText;
    private FeedState state = new FeedState();
    public String PROMPT_DATE_LABEL = "Date: ";
    public String PROMPT_TEXT_LABEL = "Prompt: ";
    public FeedViewModel(LocalDate promptDate, String promptText) {
        super("feed");
        this.promptDate = promptDate;
        this.promptText = promptText;
        initializeState();
        setLabels();
    }

    private void initializeState() {
        state.setPromptDate(promptDate);
        state.setPromptText(promptText);
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(FeedState state) {
        this.state = state;
        setLabels();
        firePropertyChanged();
    }
    private void setLabels() {
        PROMPT_DATE_LABEL = "Date: " + state.getPromptDate();
        PROMPT_TEXT_LABEL = "Prompt: " + state.getPromptText();
        support.firePropertyChange("labels", null, null);
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FeedState getState() {
        return state;
    }
}