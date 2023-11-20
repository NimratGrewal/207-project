package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;

public class DeleteController {
    final DeleteInputBoundary deleteInteractor;


    public DeleteController(DeleteInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    public void execute() {deleteInteractor.execute();}
}
