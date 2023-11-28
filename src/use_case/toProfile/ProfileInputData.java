package use_case.toProfile;

import java.util.UUID;

public class ProfileInputData {
    private final UUID userID;
    private final UUID promptID;

    public ProfileInputData(UUID userID, UUID promptID) {
        this.userID = userID;
        this.promptID = promptID;
    }

    public UUID getUserID() {
        return userID;
    }
    public UUID getPromptID() {
        return promptID;
    }

}
