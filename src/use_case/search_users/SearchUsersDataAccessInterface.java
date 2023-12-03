package use_case.search_users;

import entities.User;

import java.util.UUID;

public interface SearchUsersDataAccessInterface {

    public boolean usernameExists(String username);

    public User getUsername(String username);

}
