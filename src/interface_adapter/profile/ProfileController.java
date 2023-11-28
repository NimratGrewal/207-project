package interface_adapter.profile;

import use_case.toProfile.ProfileInputBoundary;
import use_case.toProfile.ProfileInputData;

import java.util.UUID;

public class ProfileController {
    private final ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void execute(UUID userID, UUID promptID) {
        ProfileInputData inputData = new ProfileInputData(userID, promptID);
        profileInteractor.execute(inputData);
    }
}

