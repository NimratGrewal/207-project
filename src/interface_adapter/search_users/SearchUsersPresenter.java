package interface_adapter.search_users;

import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.view_response.ViewResponseState;
import use_case.search_users.SearchUsersOutputBoundary;
import use_case.search_users.SearchUsersOutputData;

public class SearchUsersPresenter implements SearchUsersOutputBoundary {
    private SearchUsersViewModel searchUsersViewModel;

    private SearchUsersSearchBoxViewModel searchUsersSearchBoxViewModel;

    public SearchUsersPresenter(SearchUsersViewModel searchUsersViewModel) {
        this.searchUsersViewModel = searchUsersViewModel;
    }
    @Override
    public void prepareUserView(SearchUsersOutputData usersOutputData) {

    }

    @Override
    public void prepareFailView(String error) {


    }
}
