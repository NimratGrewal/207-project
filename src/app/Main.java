package app;

import data_access.DataAccessObjectFacade;
import data_access.FileUserDataAccessObject;
import data_access.PromptDataAccessObject;
import entities.CommonUserFactory;
import entities.SpotifyAPICaller;
import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import views.LoginView;
import views.ProfileView;
import views.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        LoginViewModel loginViewModel = new LoginViewModel();
        FeedViewModel feedViewModel = new FeedViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchTracksViewModel searchTracksViewModel = new SearchTracksViewModel();
        ViewResponseViewModel viewResponseViewModel = new ViewResponseViewModel();

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


        JTabbedPane tab = new JTabbedPane();
        tab.add(new JPanel());


        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


        CardLayout viewsCardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(viewsCardLayout);
        application.add(views);

        CardLayout signUpLoginViewsCardLayout = new CardLayout();
        JPanel signUpLoginViews = new JPanel(signUpLoginViewsCardLayout);

        JTabbedPane loggedInViews = new JTabbedPane();

        CardLayout promptViewCardLayout = new CardLayout();
        JPanel promptView = new JPanel(promptViewCardLayout);


    }
}
