package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toProfile.ProfileInputBoundary;
import use_case.toProfile.ProfileInteractor;
import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.UserProfileDataAccessInterface;
import views.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory() {}

    public static ProfileView create(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            UserProfileDataAccessInterface userDataAccessObject,
            FeedDataAccessInterface feedDataAccessObject) {

        try {
            ProfileController profileController = createProfileController(
                    viewManagerModel,
                    profileViewModel,
                    userDataAccessObject,
                    feedDataAccessObject
            );

            // delete controller
            DeleteController deleteController = createDeleteController();

            return new ProfileView(profileViewModel, profileController, deleteController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open data file");
        }

        return null;
    }

    private static ProfileController createProfileController(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            UserProfileDataAccessInterface userDataAccessObject,
            FeedDataAccessInterface feedDataAccessObject) throws IOException {

        ProfileOutputBoundary profilePresenter = new ProfilePresenter(viewManagerModel, profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor( userDataAccessObject,
                feedDataAccessObject, profilePresenter);

        return new ProfileController(profileInteractor);
    }

    private static DeleteController createDeleteController() {
        return  null;
    }
}
