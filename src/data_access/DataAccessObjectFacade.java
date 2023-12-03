package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import use_case.delete.DeleteResponseDataAccessInterface;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.home.HomeDataAccessInterface;
import use_case.login.LoginUserDataInterface;
import use_case.search_users.SearchUsersDataAccessInterface

import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;
import use_case.to_prompt.PromptDataAccessInterface;

import java.util.List;
import java.util.UUID;


public class DataAccessObjectFacade implements SetResponseDataAccessInterface, DeleteUserDataAccessInterface,
        UserProfileDataAccessInterface, FeedDataAccessInterface, LoginUserDataInterface, PromptDataAccessInterface,
        HomeDataAccessInterface {
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
        userDataAccessObject.deleteResponse(responseId);
        promptDataAccessObject.deleteResponse(responseId);
    }

    @Override
    public User getLoggedinUser() {
        return userDataAccessObject.getLoggedInUser();
    }

    @Override
    public Response getResponseById(UUID userId, UUID responseId) {
        return userDataAccessObject.getResponseById(userId, responseId);
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
    public Prompt getActivePrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    @Override
    public Response getLoggedInUserResponse() {
        return userDataAccessObject.getLoggedInUser().getResponseForDailyPrompt(promptDataAccessObject.getCurrentPrompt().getPromptId());
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

    @Override
    public boolean existsByName(String identifier) {
        return userDataAccessObject.existsByName(identifier);
    }

    @Override
    public boolean usernameExists(String username) {
        return userDataAccessObject.usernameExists(username);
    }

    @Override
    public User getUsername(String username) {
        UUID uuid = userDataAccessObject.getUsername(username);
        return userDataAccessObject.getUser(uuid);
    }

    public Prompt getPromptById(UUID promptId){
        return promptDataAccessObject.getPromptByID(promptId);
    }
    public void setLoggedInUser(User loggedInUser){
        userDataAccessObject.setLoggedInUser(loggedInUser);
    }

    @Override
    public void deleteLoggedInUserResponse() {

    }
}
