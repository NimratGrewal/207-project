package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.prompt.PromptController;
import interface_adapter.prompt.PromptPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toFeed.FeedInputBoundary;
import use_case.toFeed.FeedInteractor;
import use_case.toFeed.FeedOutputBoundary;
import use_case.toProfile.ProfileInputBoundary;
import use_case.toProfile.ProfileInteractor;
import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.UserProfileDataAccessInterface;
import use_case.to_prompt.PromptDataAccessInterface;
import use_case.to_prompt.PromptInputBoundary;
import use_case.to_prompt.PromptInteractor;
import use_case.to_prompt.PromptOutputBoundary;
import views.BaseView;
import views.FeedView;
import views.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class BaseViewUseCaseFactory {
    public BaseViewUseCaseFactory() {}

    public static BaseView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ViewResponseViewModel viewResponseViewModel,
            PromptDataAccessInterface promptDataAccessInterface,
            FeedViewModel feedViewModel,
            ProfileViewModel profileViewModel,
            UserProfileDataAccessInterface profileDataAccessInterface,
            FeedDataAccessInterface feedDataAccessInterface
    ) {
        PromptController promptController = createPromptUseCase(viewResponseViewModel, searchViewModel, viewManagerModel, promptDataAccessInterface);

        ProfileController profileController = null;
        try {
            profileController = createProfileController(
                    viewManagerModel,
                    profileViewModel,
                    profileDataAccessInterface
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open ProfileView!");
        }

        FeedController feedController = null;
        try {
            feedController = createFeedController(
                    viewManagerModel,
                    feedViewModel,
                    feedDataAccessInterface
            );

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not create FeedView!");
        }

        return new BaseView(profileController, feedController, promptController);
    }

    private static PromptController createPromptUseCase(ViewResponseViewModel viewResponseViewModel,
                                                       SearchViewModel searchViewModel,
                                                       ViewManagerModel viewManagerModel,
                                                       PromptDataAccessInterface promptDataAccessInterface) {
        PromptOutputBoundary promptPresenter = new PromptPresenter(viewResponseViewModel, searchViewModel, viewManagerModel);
        PromptInputBoundary promptInteractor = new PromptInteractor(promptDataAccessInterface, promptPresenter);
        return new PromptController(promptInteractor);
    }
    private static ProfileController createProfileController(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            UserProfileDataAccessInterface userDataAccessObject) throws IOException {

        ProfileOutputBoundary profilePresenter = new ProfilePresenter(viewManagerModel, profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor(userDataAccessObject, profilePresenter);

        return new ProfileController(profileInteractor);
    }

    private static FeedController createFeedController(
            ViewManagerModel viewManagerModel,
            FeedViewModel feedViewModel,
            FeedDataAccessInterface feedDataAccessObject) throws IOException {

        FeedOutputBoundary feedPresenter = new FeedPresenter(viewManagerModel, feedViewModel);
        FeedInputBoundary feedInteractor = new FeedInteractor(feedDataAccessObject, feedPresenter);

        return new FeedController(feedInteractor);
    }
}
