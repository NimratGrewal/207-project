package interface_adapter.profile;

import java.util.List;
import java.util.UUID;

public class ProfileState {
    private final UUID userID;
    private final String username;
    private final List<UUID> responseIds;
    private final int numberOfResponses;

    public ProfileState(UUID userID, String username, List<UUID> responseIds) {
        this.userID = userID;
        this.username = username;
        this.responseIds = responseIds;
        this.numberOfResponses = responseIds.size();
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

    public int getNumberOfResponses() {
        return numberOfResponses;
    }

}
