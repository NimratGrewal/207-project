package use_case.delete;

import java.util.UUID;

public class DeleteInteractor implements DeleteInputBoundary{
    final DeleteOutputBoundary deletePresenter;

    final DeleteResponseDataAccessInterface responseDataAccessInterface;

    final DeleteUserDataAccessInterface userDataAccessInterface;

    public DeleteInteractor(DeleteOutputBoundary deletePresenter, DeleteResponseDataAccessInterface responseDataAccessInterface, DeleteUserDataAccessInterface userDataAccessInterface) {
        this.deletePresenter = deletePresenter;
        this.responseDataAccessInterface = responseDataAccessInterface;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    @Override
    public void execute(DeleteInputData deleteInputData) {
        if (responseDataAccessInterface.existsbyId(deleteInputData.getResponseId())) {
            

        }
    }

}
