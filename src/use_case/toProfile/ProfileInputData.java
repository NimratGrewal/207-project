package use_case.toProfile;

import java.util.UUID;

public class ProfileInputData {
    private final UUID userID;

    public ProfileInputData(UUID userID) {
        this.userID = userID;
    }

    public UUID getUserID() {
        return userID;
    }

}
