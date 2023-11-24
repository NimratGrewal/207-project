package use_case.search_users;

import entities.User;

import java.util.UUID;

public interface SearchUsersDataAccessInterface {

    public boolean UserExists(UUID uuid);

    public User getUser(UUID uuid);

}
