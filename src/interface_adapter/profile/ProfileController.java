package interface_adapter.profile;

import use_case.toProfile.ProfileInputBoundary;
import use_case.toProfile.ProfileInputData;

import java.util.UUID;

public class ProfileController {
    final ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void execute(UUID loggedInUserId) {
        ProfileInputData inputData = new ProfileInputData(loggedInUserId);
        profileInteractor.execute(inputData);
    }
}

