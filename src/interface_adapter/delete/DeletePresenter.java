package interface_adapter.delete;

import interface_adapter.ViewManagerModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;

public class DeletePresenter implements DeleteOutputBoundary {

    private final DeleteViewModel deleteViewModel;

    private ViewManagerModel viewManagerModel;

    public DeletePresenter(DeleteViewModel deleteViewModel, ViewManagerModel viewManagerModel) {
        this.deleteViewModel = deleteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteOutputData deleteOutputData) {
        DeleteState deleteState = deleteViewModel.getState();
        deleteState.setResponseId(deleteOutputData.getResponseId());
        deleteViewModel.firePropertyChanged();

    }
}
