package use_case.delete;

import java.util.UUID;

public class DeleteInteractor implements DeleteInputBoundary{
    final DeleteOutputBoundary deletePresenter;

    public DeleteInteractor(DeleteOutputBoundary deletePresenter) {
        this.deletePresenter = deletePresenter;
    }

    @Override
    public void execute(DeleteInputData deleteInputData) {
        UUID responseId = deleteInputData.getResponseId();
        DeleteOutputData deleteOutputData = new DeleteOutputData(responseId);
        deletePresenter.prepareSuccessView(deleteOutputData);
    }

}
