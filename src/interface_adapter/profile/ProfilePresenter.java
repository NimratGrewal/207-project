package interface_adapter.profile;

import entities.Response;
import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.ProfileOutputData;
import views.ProfileView;

import java.util.List;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileView profileView;

    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
    }

    public void present(ProfileOutputData outputData) {
        // Convert the output data into a format suitable for the UI
        String username = outputData.getUsername();
        List<Response> responseHistory = outputData.getResponseHistory();
        int numberOfResponses = responseHistory.size();

        // Create a ProfileState with the necessary information
        ProfileState profileState = new ProfileState(username, responseHistory, numberOfResponses);

        // Update the UI using the ProfileView
        profileView.updateUI(profileState);
    }
}
