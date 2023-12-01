package use_case.search_users;

import entities.User;

public interface SearchUsersInputBoundary {

    void execute(SearchUsersInputData searchUsersInputData);
}
