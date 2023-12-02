package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.login.PromptDataAccessInterface;
import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;

import java.util.UUID;

public class DataAccessObjectFacade implements SetResponseDataAccessInterface, DeleteUserDataAccessInterface, UserProfileDataAccessInterface, PromptDataAccessInterface {
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
    public void deleteResponse(UUID responseId, UUID promptId) {
        userDataAccessObject.deleteResponse(responseId, promptId);
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
        promptDataAccessObject.setResponse(userDataAccessObject.getLoggedInUser().getUserId(), response);
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
    public Prompt getCurrentPrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    @Override
    public boolean answeredCurrentPrompt(UUID promptID) {
        return promptDataAccessObject.answeredCurrentPrompt(promptID);
    }

    @Override
    public User getLoggedInUser(UUID userId) {
        return userDataAccessObject.getLoggedInUser();
    }

    @Override
    public User getUser(UUID userId) {
        return userDataAccessObject.getUser(userId);
    }

}
