package use_case.search_users;

import entities.User;

public interface SearchUsersInputBoundary {
    boolean existsByName(String identifier);
    User get(String username);
}
