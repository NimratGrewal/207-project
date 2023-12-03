package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import use_case.delete.DeleteResponseDataAccessInterface;
import use_case.login.LoginUserDataInterface;
import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;

import java.util.List;
import java.util.UUID;

public class DataAccessObjectFacade implements SetResponseDataAccessInterface, DeleteResponseDataAccessInterface,
        UserProfileDataAccessInterface, FeedDataAccessInterface, LoginUserDataInterface {
    FileUserDataAccessObject userDataAccessObject;
    PromptDataAccessObject promptDataAccessObject;

    public DataAccessObjectFacade(FileUserDataAccessObject userDataAccessObject, PromptDataAccessObject promptDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
    }

    @Override
    public boolean responseExistsById(UUID responseId) {
        return userDataAccessObject.responseExistsById(responseId);
    }

    @Override
    public void deleteResponse(UUID responseId) {
        promptDataAccessObject.deleteResponse(responseId);
        userDataAccessObject.deleteResponse(responseId);
    }


    @Override
    public User getLoggedinUser() {
        return userDataAccessObject.getLoggedInUser();
    }

    @Override
    public void setResponse(Response response) {
        userDataAccessObject.setResponse(response);
        promptDataAccessObject.setResponse(userDataAccessObject.getLoggedInUserId(), response);
    }

    @Override
    public String getActivePromptText() {
        return promptDataAccessObject.getCurrentPrompt().getPromptText();
    }

    @Override
    public UUID getActivePromptId() {
        return promptDataAccessObject.getCurrentPrompt().getPromptId();
    }

    @Override
    public User getLoggedInUser() {
        return userDataAccessObject.getLoggedInUser();
    }

    @Override
    public Prompt getPromptById(UUID promptId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDataAccessObject.getAllUsers();
    }

    public List<UUID> getResponseIds(User user) {
        return userDataAccessObject.getResponseIds(user);
    }
    @Override
    public Prompt getCurrentPrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    public boolean answeredCurrentPrompt(UUID promptID) {
        return promptDataAccessObject.answeredCurrentPrompt(promptID);
    }

    public User getLoggedInUser(UUID userId) {
        return userDataAccessObject.getLoggedInUser();
    }

    @Override
    public User getUser(UUID userId) {
        return userDataAccessObject.getUser(userId);
    }

    public Response getResponseById(UUID userId, UUID responseId) {
        return userDataAccessObject.getResponseById(userId, responseId);
    }

    @Override
    public boolean existsByName(String identifier) {
        return userDataAccessObject.existsByName(identifier);
    }

    @Override
    public void setLoggedInUser(User loggedInUser){
        userDataAccessObject.setLoggedInUser(loggedInUser);
    }

}
