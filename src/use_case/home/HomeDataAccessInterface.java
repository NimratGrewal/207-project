package use_case.home;

import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface HomeDataAccessInterface {
    User getLoggedInUser();

    Prompt getCurrentPrompt();

    boolean responseExistsById(UUID responseId);

    void deleteResponse(UUID responseId);
}
