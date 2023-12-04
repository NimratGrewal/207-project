package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import use_case.delete.DeleteResponseDataAccessInterface;
import use_case.login.LoginUserDataInterface;

import use_case.search_users.SearchUsersDataAccessInterface;
import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;
import use_case.to_prompt.PromptDataAccessInterface;

import java.util.List;
import java.util.UUID;


public class DataAccessObjectFacade implements SetResponseDataAccessInterface, FeedDataAccessInterface,
    UserProfileDataAccessInterface, LoginUserDataInterface, PromptDataAccessInterface, DeleteResponseDataAccessInterface,
        SearchUsersDataAccessInterface {
    FileUserDataAccessObject userDataAccessObject;
    PromptDataAccessObject promptDataAccessObject;

    public DataAccessObjectFacade(FileUserDataAccessObject userDataAccessObject, PromptDataAccessObject promptDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
    }

    //begin set response
    @Override
    public void setResponse(Response response) {
        userDataAccessObject.setResponse(response);
        promptDataAccessObject.setResponse(userDataAccessObject.getLoggedInUserId(), response);
    }

    //set response
    @Override
    public String getActivePromptText() {
        return promptDataAccessObject.getCurrentPrompt().getPromptText();
    }

    //set response
    @Override
    public UUID getActivePromptId() {
        return promptDataAccessObject.getCurrentPrompt().getPromptId();
    }

    // set response
    @Override
    public User getLoggedInUser() {
        return userDataAccessObject.getLoggedInUser();
    }
    //end set response

    //begin feed
    @Override
    public Prompt getCurrentPrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    //feed
    @Override
    public List<User> getAllUsers() {
        return userDataAccessObject.getAllUsers();
    }
    //end feed

    //begin profile
    @Override
    public Prompt getPromptById(UUID promptId) {
        return promptDataAccessObject.getPromptByID(promptId);
    }

    //profile
    @Override
    public User getUser(UUID userId) {
        return userDataAccessObject.getUser(userId);
    }
    //end profile

    //begin login
    @Override
    public boolean existsByName(String identifier) {
        return userDataAccessObject.existsByName(identifier);
    }

    //login
    @Override
    public void setLoggedInUser(User user) {
        userDataAccessObject.setLoggedInUser(user);
    }
    //end login


    //begin prompt
    @Override
    public Prompt getActivePrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    //prompt
    @Override
    public Response getLoggedInUserResponse() {
        return null;
    }
    //end prompt

    //begin delete
    @Override
    public boolean responseExistsById(UUID responseId) {
        return userDataAccessObject.responseExistsById(responseId);
    }

    //delete
    @Override
    public void deleteResponse(UUID responseId) {
        userDataAccessObject.deleteResponse(responseId);
        promptDataAccessObject.deleteResponse(responseId);
    }

    //delete
    @Override
    public User getLoggedinUser() {
        return userDataAccessObject.getLoggedInUser();
    }

    //delete
    @Override
    public Response getResponseById(UUID userId, UUID responseId) {
        return userDataAccessObject.getResponseById(userId, responseId);
    }
    //end delete

    //begin search users
    @Override
    public boolean usernameExists(String username) {
        return userDataAccessObject.usernameExists(username);
    }

    //search users
    @Override
    public User getUsername(String username) {
        UUID uuid = userDataAccessObject.getUsername(username);
        return userDataAccessObject.getUser(uuid);
    }
    //end search users
}
