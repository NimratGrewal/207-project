package views;

import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksController;
import interface_adapter.search_tracks.SearchTracksState;
import interface_adapter.search_tracks.SearchTracksViewModel;
import views.components.SearchResultBox;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements PropertyChangeListener {
    private final SearchViewModel searchViewModel;
    private final SearchTracksController searchTracksController;
    private final SearchTracksViewModel searchTracksViewModel;

    private JLabel promptText;
    private JList<SearchResultBox> searchResults;
    public SearchView (SearchViewModel searchViewModel,
                       SearchTracksController searchTracksController,
                       SearchTracksViewModel searchTracksViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        this.searchTracksController = searchTracksController;
        this.searchTracksViewModel = searchTracksViewModel;
        this.searchTracksViewModel.addPropertyChangeListener(this);

        promptText = new JLabel(SearchViewModel.TITLE_LABEL);
        JTextField searchBarField = new JTextField();
        searchResults = new JList<>();
        JButton setResponse = new JButton(SearchViewModel.SET_RESPONSE_LABEL);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchState state) {
            //render prompt text
        } else if (evt.getNewValue() instanceof SearchTracksState state) {
            //render tracklist
        }
    }
}
