package use_case.login;
import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface LoginUserDataInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);

    User getUser(UUID userid);
    Prompt getCurrentPrompt();
    void setLoggedInUser(User user);

}
