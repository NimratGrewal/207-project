package views;

import entities.Prompt;
import entities.Response;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ProfileResponseBox extends FeedResponseBox {
    private final PromptDataAccessObject promptDataAccessObject;
    public ProfileResponseBox(Response response, PromptDataAccessObject promptDataAccessObject) {
        super(response);
        this.promptDataAccessObject = promptDataAccessObject;

        UUID promptID = response.getPromptId();
        Prompt prompt = promptDataAccessObject.getPromptById(promptID);

        JPanel topPanel = (JPanel) getComponent(0);

        JLabel dateLabel = new JLabel("Date: " + prompt.getPromptDate());
        JLabel promptLabel = new JLabel("Prompt: " + prompt.getPromptText());
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        topPanel.add(dateLabel, BorderLayout.NORTH);
        topPanel.add(promptLabel, BorderLayout.NORTH);
        topPanel.remove(0);
    }
}
