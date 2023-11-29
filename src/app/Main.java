package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

//        JFrame application = new JFrame("Login Example");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//        JPanel views = new JPanel(cardLayout);
//
//        CardLayout signUpLoginCardLayout = new CardLayout();
//        JPanel signUpLoginViews = new JPanel(signUpLoginCardLayout);
//
//        JTabbedPane loggedInViews = new JTabbedPane();
//
//        CardLayout promptViewsCardLayout = new CardLayout();
//        JPanel promptView = new JPanel(promptViewsCardLayout);
//
//        application.add(views);
//        application.add(signUpLoginViews);
//        application.add(loggedInViews);
//        application.add(promptView);
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        ViewManager viewManager = new ViewManager(cardLayout, views, signUpLoginViews, signUpLoginCardLayout,
//                loggedInViews, promptView, promptViewsCardLayout, viewManagerModel);
//
//        SearchViewModel searchViewModel = new SearchViewModel();
//        ViewResponseViewModel viewResponseViewModel = new ViewResponseViewModel();
//        FeedViewModel feedViewModel = new FeedViewModel();
//        ProfileViewModel profileViewModel = new ProfileViewModel();
//        SearchTracksViewModel searchTracksViewModel = new SearchTracksViewModel("home");
//
//        SearchView searchView = new SearchView(searchViewModel, searchTracksViewModel);
//        ViewResponseView viewResponseView = new ViewResponseView(viewResponseViewModel);
//        FeedView feedView = new FeedView(feedViewModel);
//        ProfileView profileView = new ProfileView(profileViewModel);
//
//        BaseView baseView = new BaseView(searchView, viewResponseView, feedView, profileView,
//                UUID.randomUUID(), UUID.randomUUID());
//
//        loggedInViews.add(feedView, feedView.viewName);
//        loggedInViews.add(profileView, profileView.viewName);
//
//        viewManagerModel.setActiveView(feedView.viewName);
//        viewManagerModel.firePropertyChanged();
//
//        application.pack();
//        application.setVisible(true);
    }
}

