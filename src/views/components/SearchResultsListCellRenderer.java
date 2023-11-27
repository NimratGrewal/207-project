package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SearchResultsListCellRenderer extends JPanel implements ListCellRenderer<Map<String, String>> {

    public SearchResultsListCellRenderer() {
        this.setPreferredSize(new Dimension(300, 50));
        this.setOpaque(true);
        this.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Map<String, String>> list,
                                                  Map<String, String> value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if (isSelected) {
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
        } else {
            this.setForeground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        JLabel songName = new JLabel(value.get("id"));
        JLabel albumName = new JLabel(value.get("album"));
        JLabel artists = new JLabel(value.get("artists"));

        this.add(songName);
        this.add(albumName);
        this.add(artists);

        return this;
    }
}
