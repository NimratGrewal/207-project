package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
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
            UserProfileDataAccessInterface userDataAccessObject) {

        DeleteController deleteController = createDeleteController();

        return new ProfileView(profileViewModel, deleteController);
    }

    private static DeleteController createDeleteController() {
        return  null;
    }
}
