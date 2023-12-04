package use_case.search_users;

public interface SearchUsersInputBoundary {

    void execute(SearchUsersInputData searchUsersInputData);

    void profileToSearch();
}
