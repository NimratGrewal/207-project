package interface_adapter.search_users;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.view_response.ViewResponseState;
import use_case.search_users.SearchUsersOutputBoundary;
import use_case.search_users.SearchUsersOutputData;

public class SearchUsersPresenter implements SearchUsersOutputBoundary {
    private final SearchUsersViewModel searchUsersViewModel;
    private SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel;

    private ViewManagerModel viewManagerModel;

    public SearchUsersPresenter(SearchUsersViewModel searchUsersViewModel, SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel,
                                ViewManagerModel viewManagerModel) {
        this.searchUsersViewModel = searchUsersViewModel;
        this.searchUsersSearchBoxViewModel = searchUsersSearchBoxViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareUserView(SearchUsersOutputData usersOutputData) {
        SearchUsersState searchUsersState = searchUsersViewModel.getState();
        searchUsersState.setUsername(usersOutputData.getUsername());
        searchUsersState.setNumberOfResponses(usersOutputData.getNumberOfResponses());
        searchUsersState.setResponseInfoMap(usersOutputData.getResponseInfoMap());
        this.searchUsersViewModel.setState(searchUsersState);
        this.searchUsersViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchUsersViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchUsersSearchBoxState searchUsersSearchBoxState = searchUsersSearchBoxViewModel.getState();
        searchUsersSearchBoxState.setUsernameError(error);
        searchUsersSearchBoxViewModel.firePropertyChanged();

    }

    // change from profile to search view
    public void prepareSearchView(){
        SearchUsersSearchBoxState searchUsersSearchBoxState = searchUsersSearchBoxViewModel.getState();
        // reset the search view model to no username
        searchUsersSearchBoxState.setUsername("");
        searchUsersSearchBoxViewModel.firePropertyChanged();

        // change the view
        this.viewManagerModel.setActiveView(searchUsersViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
