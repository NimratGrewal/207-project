package views;

import interface_adapter.search.SearchState;
import interface_adapter.search_tracks.SearchTracksState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    @Override
    public void actionPerformed(ActionEvent e) {

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
