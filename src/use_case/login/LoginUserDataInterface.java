package use_case.login;
import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface LoginUserDataInterface {
    boolean existsByName(String identifier);

    void setLoggedInUser(User user);

    User getUser(UUID userId);
    Prompt getCurrentPrompt();
}
