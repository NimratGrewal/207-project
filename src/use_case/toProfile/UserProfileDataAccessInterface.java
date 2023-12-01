package use_case.toProfile;

import entities.Response;
import entities.User;

import java.util.List;
import java.util.UUID;

public interface UserProfileDataAccessInterface {

    User getUser(UUID userId);

    Response getResponseById(UUID responseId);
    List<User> getAllUsers();
}
