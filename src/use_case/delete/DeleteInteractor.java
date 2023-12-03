package use_case.delete;

import java.util.UUID;

import entities.Prompt;
import entities.Response;
import entities.User;

public class DeleteInteractor implements DeleteInputBoundary {
    final DeleteOutputBoundary deletePresenter;

    final DeleteResponseDataAccessInterface responseDataAccessInterface;


    public DeleteInteractor(DeleteOutputBoundary deletePresenter, DeleteResponseDataAccessInterface responseDataAccessInterface) {
        this.deletePresenter = deletePresenter;
        this.responseDataAccessInterface = responseDataAccessInterface;
    }

    @Override
    public void execute(DeleteInputData deleteInputData) {
        UUID responseId = deleteInputData.getResponseId();
        // find response object from the response id:
        User user = responseDataAccessInterface.getLoggedinUser();
        UUID loggedInUserId = user.getUserId();

        // call the response method in the user data access interface -> get response object
        Response response = responseDataAccessInterface.getResponseById(loggedInUserId, responseId);

        // pass in response object into the data access object to find the prompt id -> delete both
        UUID promptId = response.getPromptId();

        if (responseDataAccessInterface.responseExistsById(responseId)) {
            if (responseDataAccessInterface.responseExistsById(responseId)) {

                responseDataAccessInterface.deleteResponse(responseId);

                DeleteOutputData deleteOutputData = new DeleteOutputData(responseId);
                deletePresenter.prepareSuccessView(deleteOutputData);
            }

        }
    }
}
