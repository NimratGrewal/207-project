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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class SearchView extends JPanel implements PropertyChangeListener {
    private final SearchViewModel searchViewModel;
    private final SearchTracksViewModel searchTracksViewModel;

    private final JLabel promptText;
    private final JList<Map<String, String>> searchResults;
    private final ListModel<Map<String, String>> listModel;
    private final JTextField searchBarField;
    private final JButton setResponse;
    public SearchView (SearchViewModel searchViewModel,
                       SearchTracksViewModel searchTracksViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        this.searchTracksViewModel = searchTracksViewModel;
        this.searchTracksViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        promptText = new JLabel();

        searchBarField = new JTextField();

        searchResults = new JList<>();

        listModel = new DefaultListModel<>();
        searchResults.setModel(listModel);
        searchResults.setCellRenderer(new SearchResultsListCellRenderer());
        searchResults.setLayoutOrientation(JList.VERTICAL);
        searchResults.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScroller = new JScrollPane(searchResults);
        listScroller.setPreferredSize(new Dimension(300, 50));

        setResponse = new JButton(SearchViewModel.SET_RESPONSE_LABEL);
        setResponse.setEnabled(false);

        searchResults.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        setResponse.setEnabled(!searchResults.isSelectionEmpty());
                    }
                }
        );

        searchBarField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        searchViewModel.getState().setSearchBarText(searchBarField.getText() + e.getKeyChar());
                        searchTracksController.execute(searchViewModel.getState().getSearchBarText());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        setResponse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(setResponse)) {
                            setResponseController.execute(listModel.getElementAt(searchResults.getSelectedIndex()).get("id"));
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(promptText);
        this.add(listScroller);
        this.add(setResponse);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchState state) {
            promptText.setText(state.getPromptText());
        } else if (evt.getNewValue() instanceof SearchTracksState state) {
            searchResults.getSelectionModel().clearSelection();
            ((DefaultListModel<Map<String, String>>) listModel).clear();
            for (Map<String, String> songInfo: state.getTrackList()) {
                ((DefaultListModel<Map<String, String>>) listModel).addElement(songInfo);
            }
        }
    }
}
