package interface_adapter.profile;

import entities.Response;
import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.ProfileOutputData;

import java.util.List;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;

    public ProfilePresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void present(ProfileOutputData outputData) {
        String username = outputData.getUsername();
        List<Response> responseHistory = outputData.getResponseHistory();
        int numberOfResponses = responseHistory.size();

        ProfileState profileState = new ProfileState(username, responseHistory, numberOfResponses);

        profileViewModel.setState(profileState);
    }
}

