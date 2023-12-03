package use_case.toProfile;

import entities.Prompt;
import entities.Response;
import entities.User;

import java.util.UUID;

public interface UserProfileDataAccessInterface {

    User getLoggedInUser();
    Prompt getPromptById(UUID promptId);
    User getUser(UUID userId);
}
