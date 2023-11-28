package interface_adapter.profile;

import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.ProfileOutputData;

import java.util.Map;
import java.util.UUID;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;

    public ProfilePresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void present(ProfileOutputData outputData) {
        String username = outputData.getUsername();
        int numberOfResponses = outputData.getNumberOfResponses();
        Map<UUID, Map<String, Object>> responseInfoMap = outputData.getResponseInfoMap();

        ProfileState profileState = new ProfileState(username, numberOfResponses, responseInfoMap);
        profileViewModel.setState(profileState);
    }
}

