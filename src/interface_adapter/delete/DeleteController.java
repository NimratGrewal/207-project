package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;
import java.util.UUID;

public class DeleteController {
    final DeleteInputBoundary deleteInteractor;


    public DeleteController(DeleteInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    public void execute(UUID promptId) {
        DeleteInputData deleteInputData = new DeleteInputData(promptId);

        deleteInteractor.execute(deleteInputData);}
}
