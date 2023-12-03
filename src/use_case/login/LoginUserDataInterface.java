package use_case.login;
import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface LoginUserDataInterface {
    boolean existsByName(String identifier);
    User getUsername(String username);

    User getUser(UUID userid);
    Prompt getCurrentPrompt();
    void setLoggedInUser(User user);

}
