package views;

import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksController;
import interface_adapter.search_tracks.SearchTracksState;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.set_response.SetResponseController;
import views.components.SearchResultsListCellRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class SearchView extends JPanel implements PropertyChangeListener {
    private final SearchViewModel searchViewModel;
    private final SearchTracksController searchTracksController;
    private final SetResponseController setResponseController;
    private final SearchTracksViewModel searchTracksViewModel;

    private final JLabel promptText;
    private final JList<Map<String, String>> searchResults;
    private final ListModel<Map<String, String>> listModel;
    private final JTextField searchBarField;
    private final JButton searchButton;
    private final JButton setResponse;
    public SearchView (SearchViewModel searchViewModel,
                       SearchTracksController searchTracksController,
                       SetResponseController setResponseController,
                       SearchTracksViewModel searchTracksViewModel) {
        this.searchViewModel = searchViewModel;
        this.setResponseController = setResponseController;
        this.searchViewModel.addPropertyChangeListener(this);

        this.searchTracksController = searchTracksController;
        this.searchTracksViewModel = searchTracksViewModel;
        this.searchTracksViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        promptText = new JLabel();

        searchBarField = new JTextField();
        searchBarField.setPreferredSize(new Dimension(200, 18));
        searchButton = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        JPanel searchBarPanel = new JPanel();

        searchBarPanel.add(searchBarField);
        searchBarPanel.add(searchButton);

        searchResults = new JList<>();

        listModel = new DefaultListModel<>();
        searchResults.setModel(listModel);
        searchResults.setCellRenderer(new SearchResultsListCellRenderer());
        searchResults.setLayoutOrientation(JList.VERTICAL);
        searchResults.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScroller = new JScrollPane(searchResults);
        listScroller.setPreferredSize(new Dimension(500, 300));

        setResponse = new JButton(SearchViewModel.SET_RESPONSE_LABEL);
        setResponse.setEnabled(false);

        searchResults.addListSelectionListener(
                e -> setResponse.setEnabled(!searchResults.isSelectionEmpty())
        );

        searchBarField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        searchViewModel.getState().setSearchBarText(searchBarField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        searchButton.addActionListener(
                e -> {
                    if (e.getSource().equals(searchButton)) {
                        searchTracksController.execute(searchViewModel.getState().getSearchBarText());
                    }
                }
        );

        setResponse.addActionListener(
                e -> {
                    if (e.getSource().equals(setResponse)) {
                        setResponseController.execute(listModel.getElementAt(searchResults.getSelectedIndex()).get("id"));
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(promptText);
        this.add(searchBarPanel);
        this.add(listScroller);
        this.add(setResponse);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchState state) {
            promptText.setText(state.getPromptText());
        } else if (evt.getNewValue() instanceof SearchTracksState state) {
            searchResults.getSelectionModel().clearSelection();
            ((DefaultListModel<Map<String, String>>) listModel).removeAllElements();
            for (Map<String, String> songInfo: state.getTrackList()) {
                ((DefaultListModel<Map<String, String>>) listModel).addElement(songInfo);
            }
        }
    }
}
