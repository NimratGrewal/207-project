package interface_adapter.profile;

import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.ProfileOutputData;

import java.util.List;
import java.util.UUID;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;

    public ProfilePresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void present(ProfileOutputData outputData) {
        UUID userID = outputData.getUserID();
        String username = outputData.getUsername();
        List<UUID> responseIds = outputData.getResponseIds();

        ProfileState profileState = new ProfileState(userID, username, responseIds);
        profileViewModel.setState(profileState);
    }
}

