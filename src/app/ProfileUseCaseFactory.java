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
import use_case.delete.DeleteResponseDataAccessInterface;
import views.ProfileView;


public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory() {}

    public static ProfileView create(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            DeleteResponseDataAccessInterface deleteResponseDataAccessInterface) {

        DeleteController deleteController = createDeleteController(profileViewModel, viewManagerModel, deleteResponseDataAccessInterface);

        return new ProfileView(profileViewModel, deleteController);
    }

    private static DeleteController createDeleteController(
            ProfileViewModel profileViewModel,
            ViewManagerModel viewManagerModel,
            DeleteResponseDataAccessInterface deleteResponseDataAccessInterface
    ) {
        DeleteOutputBoundary deletePresenter = new DeletePresenter(profileViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(deletePresenter, deleteResponseDataAccessInterface);

        return new DeleteController(deleteInteractor);
    }
}
