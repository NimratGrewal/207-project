package use_case.toProfile;

import entities.Response;
import entities.User;

import java.util.UUID;

public interface UserProfileDataAccessInterface {
    User getLoggedInUser(UUID userId);

    User getUser(UUID userId);

    Response getResponseById(UUID responseId);
}
