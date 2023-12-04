package app;

import data_access.DataAccessObjectFacade;
import data_access.FileUserDataAccessObject;
import data_access.PromptDataAccessObject;
import entities.CommonUserFactory;
import entities.SpotifyAPICaller;
import entities.User;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.search_users.SearchUsersSearchBoxViewModel;
import interface_adapter.search_users.SearchUsersViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Our App!");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SpotifyAPICaller caller = new SpotifyAPICaller(System.getenv("CLIENT_ID"), System.getenv("CLIENT_SECRET"));

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        FeedViewModel feedViewModel = new FeedViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchTracksViewModel searchTracksViewModel = new SearchTracksViewModel();
        ViewResponseViewModel viewResponseViewModel = new ViewResponseViewModel();
        SearchUsersViewModel searchUsersViewModel = new SearchUsersViewModel();
        SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel = new SearchUsersSearchBoxViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./src/users.csv", new CommonUserFactory(), caller);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PromptDataAccessObject promptDataAccessObject;
        try {
            promptDataAccessObject = new PromptDataAccessObject("./src/prompts.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataAccessObjectFacade dataAccessObject = new DataAccessObjectFacade(userDataAccessObject, promptDataAccessObject);

        UserFactory uf = new CommonUserFactory();
        User u1 = uf.create(UUID.fromString("78920e42-5a5b-45ad-93fe-4f4a93d61311"), "momo", "abc", LocalDateTime.parse("2007-12-03T10:15:30"));
        User u2 = uf.create(UUID.fromString("8c7664fc-63d8-446d-bb04-d7e638114e21"), "haya", "123", LocalDateTime.parse("2007-12-03T10:15:30"));
        userDataAccessObject.save(u1);
        userDataAccessObject.save(u2);
        userDataAccessObject.setLoggedInUser(u1);

        CardLayout loginSignupCardLayout = new CardLayout();
        JPanel loginSignUpViews = new JPanel(loginSignupCardLayout);

        JPanel startView = StartUseCaseFactory.create(loginViewModel, signupViewModel, viewManagerModel);

        JPanel loginView = LoginUseCaseFactory.create(loginViewModel, viewManagerModel, searchViewModel, viewResponseViewModel,
                dataAccessObject);
        loginView.setName("log in");

        JPanel signUpView = SignUpUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, dataAccessObject);

        loginSignUpViews.add("log in", loginView);
        loginSignUpViews.add("signup", signUpView);
        loginSignUpViews.add("start", startView);
        loginSignUpViews.setName("login signup");

        BaseView loggedInViews = BaseViewUseCaseFactory.create(viewManagerModel, searchViewModel, viewResponseViewModel,
                dataAccessObject, feedViewModel, profileViewModel, dataAccessObject, dataAccessObject, dataAccessObject, searchUsersViewModel, searchUsersSearchBoxViewModel);

        CardLayout promptViewCardLayout = new CardLayout();
        JPanel promptView = new JPanel(promptViewCardLayout);
        JPanel searchView = SearchUseCaseFactory.create(searchViewModel, searchTracksViewModel, viewManagerModel,
                dataAccessObject, caller, viewResponseViewModel);
        JPanel viewResponseView = ViewResponseViewUseCaseFactory.create(viewManagerModel, searchViewModel, viewResponseViewModel, dataAccessObject);
        promptView.add("search", searchView);
        promptView.add("view response", viewResponseView);
        promptView.setName("prompt");

        FeedView feedView = FeedUseCaseFactory.create(feedViewModel);
        feedView.setName("feed");

        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, profileViewModel, dataAccessObject);
        profileView.setName("profile");

        CardLayout searchUsersViewCardLayout = new CardLayout();
        JPanel searchUsersViews = new JPanel(searchUsersViewCardLayout);
        searchUsersViews.setName("search user");

        SearchUserView searchUserView = SearchUserUseCaseFactory.create(dataAccessObject, searchUsersViewModel, searchUsersSearchBoxViewModel, viewManagerModel);
        searchUserView.setName("search users");
        SearchUserProfileView searchUserProfileView = SearchUsersProfileUseCaseFactory.create(dataAccessObject, searchUsersViewModel, searchUsersSearchBoxViewModel, viewManagerModel);
        searchUserProfileView.setName("search users profile");

        searchUsersViews.add(searchUserView.getName(), searchUserView);
        searchUsersViews.add(searchUserProfileView.getName(), searchUserProfileView);

        loggedInViews.add("prompt", promptView);
        loggedInViews.add("feed", feedView);
        loggedInViews.add("profile", profileView);
        loggedInViews.add("search user", searchUsersViews);

        loggedInViews.setName("logged in");

        CardLayout viewsCardLayout = new CardLayout();
        JPanel views = new JPanel(viewsCardLayout);
        application.add(views);

        views.add(loginSignUpViews.getName(), loginSignUpViews);
        views.add(loggedInViews.getName(), loggedInViews);

        new ViewManager(viewsCardLayout, views, loginSignUpViews, loginSignupCardLayout, loggedInViews,
                searchUsersViews, searchUsersViewCardLayout, promptView, promptViewCardLayout, viewManagerModel);

        viewManagerModel.setActiveView("start");
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
