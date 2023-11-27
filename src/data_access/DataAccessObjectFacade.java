package data_access;

import entities.Prompt;
import entities.Response;
import entities.User;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.login.PromptDataAccessInterface;
import use_case.set_response.SetResponseDataAccessInterface;
import use_case.toProfile.UserProfileDataAccessInterface;

import java.util.List;
import java.util.UUID;

public class DataAccessObjectFacade implements SetResponseDataAccessInterface, DeleteUserDataAccessInterface, UserProfileDataAccessInterface, PromptDataAccessInterface {
    FileUserDataAccessObject userDataAccessObject;
    PromptDataAccessObject promptDataAccessObject;
    public DataAccessObjectFacade(FileUserDataAccessObject userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
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
    public User get(UUID userId) {
        return userDataAccessObject.get(userId);
    }

    @Override
    public List<UUID> getResponseIds(User user) {
        return userDataAccessObject.getResponseIds(user);
    }

    @Override
    public Prompt getCurrentPrompt() {
        return promptDataAccessObject.getCurrentPrompt();
    }

    @Override
    public boolean answeredCurrentPrompt(UUID promptID) {
        return promptDataAccessObject.answeredCurrentPrompt(promptID);
    }
}
