package use_case.toProfile;

import entities.Response;
import entities.User;

import java.util.List;
import java.util.UUID;

// Modified ProfileInteractor
public class ProfileInteractor implements ProfileInputBoundary {
    private final UserProfileDataAccessInterface userDataAccessObject;
    private final ProfileOutputBoundary presenter;

    public ProfileInteractor(UserProfileDataAccessInterface userDataAccessObject, ProfileOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(ProfileInputData inputData) {
        UUID userId = inputData.getUserId();
        User user = userDataAccessObject.get(userId);

        // Assuming getUsername is a method in the User class
        String username = user.getUsername();

        List<UUID> responseIds = userDataAccessObject.getResponseIds(user);

        // Create ProfileOutputData with the required information
        ProfileOutputData outputData = new ProfileOutputData(username, responseIds);

        // Pass the output data to the presenter
        presenter.present(outputData);
    }
}

