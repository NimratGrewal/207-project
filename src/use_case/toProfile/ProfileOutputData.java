package use_case.toProfile;

import entities.Response;

import java.util.List;
import java.util.UUID;

public class ProfileOutputData {
    private final UUID userID;
    private final String username;
    private final List<UUID> responseIds;

    public ProfileOutputData(UUID userID, String username, List<UUID> responseIds) {
        this.userID = userID;
        this.username = username;
        this.responseIds = responseIds;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<UUID> getResponseIds() {
        return responseIds;
    }
}
