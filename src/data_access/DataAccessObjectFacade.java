package data_access;

import entities.Response;
import entities.User;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.set_response.SetResponseDataAccessInterface;

import java.util.UUID;

public class DataAccessObjectFacade implements SetResponseDataAccessInterface, DeleteUserDataAccessInterface {
    FileUserDataAccessObject userDataAccessObject;
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
    }

    @Override
    public void setResponse(Response response) {
        userDataAccessObject.setResponse(response);
    }

    @Override
    public String getActivePromptText() {
        return null;
    }

    @Override
    public UUID getActivePromptId() {
        return null;
    }

    @Override
    public User getLoggedInUser() {
        return userDataAccessObject.getLoggedInUser();
    }
}
