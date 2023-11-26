package interface_adapter.profile;

import entities.Response;

import java.util.List;
import java.util.UUID;

public class ProfileState {
    private final String username;
    private final List<UUID> responseIds;
    private final int numberOfResponses;

    public ProfileState(String username, List<UUID> responseIds) {
        this.username = username;
        this.responseIds = responseIds;
        this.numberOfResponses = responseIds.size();
    }

    public String getUsername() {
        return username;
    }

    public List<UUID> getResponseIds() {
        return responseIds;
    }

    public int getNumberOfResponses() {
        return numberOfResponses;
    }
}
