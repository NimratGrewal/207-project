package use_case.toProfile;

import entities.User;

import java.util.UUID;

public interface UserProfileDataAccessInterface {
    User getUser(UUID userId);
}
