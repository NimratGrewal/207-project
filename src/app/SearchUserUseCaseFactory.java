package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_users.SearchUsersController;
import interface_adapter.search_users.SearchUsersPresenter;
import interface_adapter.search_users.SearchUsersSearchBoxViewModel;
import interface_adapter.search_users.SearchUsersViewModel;
import use_case.search_users.SearchUsersDataAccessInterface;
import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInteractor;
import use_case.search_users.SearchUsersOutputBoundary;
import views.SearchUserView;

public class SearchUserUseCaseFactory {
    public static SearchUserView create(
            SearchUsersDataAccessInterface searchUsersDataAccessInterface,
            SearchUsersViewModel searchUsersViewModel,
            SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel,
            ViewManagerModel viewManagerModel
    ) {
        SearchUsersController searchUsersController = createSearchUsersController(searchUsersDataAccessInterface,
                searchUsersViewModel, searchUsersSearchBoxViewModel, viewManagerModel);
        return new SearchUserView(searchUsersController, searchUsersSearchBoxViewModel);
    }

    private static SearchUsersController createSearchUsersController(
            SearchUsersDataAccessInterface searchUsersDataAccessInterface,
            SearchUsersViewModel searchUsersViewModel,
            SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel,
            ViewManagerModel viewManagerModel
    ) {
        SearchUsersOutputBoundary searchUsersPresenter = new SearchUsersPresenter(searchUsersViewModel, searchUsersSearchBoxViewModel, viewManagerModel);
        SearchUsersInputBoundary searchUsersInteractor = new SearchUsersInteractor(searchUsersDataAccessInterface, searchUsersPresenter);
        return new SearchUsersController(searchUsersInteractor);
    }
}
