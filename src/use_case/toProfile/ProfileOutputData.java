package use_case.toProfile;

import entities.Response;

import java.util.List;
import java.util.UUID;

public class ProfileOutputData {
    private final String username;
    private final List<UUID> responseIds;

    public ProfileOutputData(String username, List<UUID> responseIds) {
        this.username = username;
        this.responseIds = responseIds;
    }

    public String getUsername() {
        return username;
    }

    public List<UUID> getResponseIds() {
        return responseIds;
    }
}
