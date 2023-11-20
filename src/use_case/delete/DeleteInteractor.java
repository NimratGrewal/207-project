package use_case.delete;

public class DeleteInteractor implements DeleteInputBoundary{
    final DeleteOutputBoundary deletePresenter;

    final DeleteResponseDataAccessInterface deleteResponseDataAccessInterface;

    final DeleteUserDataAccessInterface deleteUserDataAccessInterface;

    public DeleteInteractor(DeleteOutputBoundary deletePresenter, DeleteResponseDataAccessInterface deleteResponseDataAccessInterface, DeleteUserDataAccessInterface deleteUserDataAccessInterface) {
        this.deletePresenter = deletePresenter;
        this.deleteResponseDataAccessInterface = deleteResponseDataAccessInterface;
        this.deleteUserDataAccessInterface = deleteUserDataAccessInterface;
    }

    @Override
    public void execute() {

    }
}
