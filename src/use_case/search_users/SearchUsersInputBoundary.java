package use_case.search_users;

import entities.User;

public interface SearchUsersInputBoundary {

    void execute(SearchUsersInputData searchUsersInputData);

    void profile_to_search();
}
