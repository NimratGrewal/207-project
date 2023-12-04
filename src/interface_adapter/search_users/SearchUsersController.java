package interface_adapter.search_users;


import use_case.search_users.SearchUsersInputBoundary;
import use_case.search_users.SearchUsersInputData;

public class SearchUsersController {
    private final SearchUsersInputBoundary searchUsersInteractor;

    public SearchUsersController(SearchUsersInputBoundary searchUsersInteractor) {
        this.searchUsersInteractor = searchUsersInteractor;
    }

    public void execute(String username) {
        SearchUsersInputData searchTracksInputData = new SearchUsersInputData(username);
        searchUsersInteractor.execute(searchTracksInputData);
    }
    public void profileToSearch(){
        searchUsersInteractor.profileToSearch();
    }
}

