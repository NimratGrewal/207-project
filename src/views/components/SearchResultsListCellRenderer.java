package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SearchResultsListCellRenderer extends JPanel implements ListCellRenderer<Map<String, String>> {

    private JLabel lbIcon = new JLabel();
    private JLabel name = new JLabel();
    private JLabel artists = new JLabel();
    private JLabel album = new JLabel();

    public SearchResultsListCellRenderer() {
        setLayout(new BorderLayout(5, 5));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(name);
        panelText.add(artists);
        panelText.add(album);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Map<String, String>> list,
                                                  Map<String, String> value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        name.setText(value.get("name"));
        artists.setText(value.get("artists"));
        album.setText(value.get("album"));

        if (isSelected) {
            name.setForeground(list.getSelectionForeground());
            artists.setForeground(list.getSelectionForeground());
            album.setForeground(list.getSelectionForeground());
            name.setBackground(list.getSelectionBackground());
            artists.setBackground(list.getSelectionBackground());
            album.setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            name.setForeground(list.getForeground());
            artists.setForeground(list.getForeground());
            album.setForeground(list.getForeground());
            name.setBackground(list.getBackground());
            artists.setBackground(list.getBackground());
            album.setBackground(list.getBackground());
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        return this;
    }
}
