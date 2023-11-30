package use_case.toProfile;

import java.util.UUID;

public class ProfileInputData {
    private final UUID loggedInUserID;

    public ProfileInputData(UUID loggedInUserID) {
        this.loggedInUserID = loggedInUserID;
    }

    public UUID getLoggedInUserID() {
        return loggedInUserID;
    }

}
