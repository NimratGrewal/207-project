package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SearchResultsListCellRenderer extends JPanel implements ListCellRenderer<Map<String, String>> {

    public SearchResultsListCellRenderer() {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Map<String, String>> list,
                                                  Map<String, String> value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        return null;
    }
}
