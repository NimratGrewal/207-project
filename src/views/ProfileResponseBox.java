package views;

import entities.Response;

import javax.swing.*;
import java.awt.*;

public class ProfileResponseBox extends views.FeedResponseBox {
    public ProfileResponseBox(Response response) {
        super(response);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
    }
}
