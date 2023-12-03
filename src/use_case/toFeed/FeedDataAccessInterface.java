package use_case.toFeed;

import entities.Prompt;
import entities.Response;
import entities.User;

import java.util.List;
import java.util.UUID;

public interface FeedDataAccessInterface {

    Prompt getCurrentPrompt();

    List<User> getAllUsers();

}
