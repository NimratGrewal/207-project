package use_case.search_users;


public interface SearchUsersOutputBoundary {
    void prepareUserView(SearchUsersOutputData usersOutputData);

    void prepareFailView(String error);

    void prepareSearchView();
}
