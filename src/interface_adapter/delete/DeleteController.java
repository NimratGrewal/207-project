package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;
import java.util.UUID;

public class DeleteController {
    final DeleteInputBoundary deleteInteractor;


    public DeleteController(DeleteInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    public void execute(UUID responseId) {
        DeleteInputData deleteInputData = new DeleteInputData(responseId);

        deleteInteractor.execute(deleteInputData);}
}
