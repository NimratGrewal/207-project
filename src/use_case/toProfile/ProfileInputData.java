package use_case.toProfile;

import java.util.UUID;

public class ProfileInputData {
    private final UUID userId;

    public ProfileInputData(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

}
