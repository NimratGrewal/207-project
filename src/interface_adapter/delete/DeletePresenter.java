package interface_adapter.delete;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;

public class DeletePresenter implements DeleteOutputBoundary {

    private final ProfileViewModel profileViewModel;

    private ViewManagerModel viewManagerModel;

    public DeletePresenter(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteOutputData deleteOutputData) {
        ProfileState profileState = profileViewModel.getState();
        profileState.getResponseInfoMap().remove(deleteOutputData.getResponseId());
        profileState.setNumberOfResponses(profileState.getNumberOfResponses() - 1);
        profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

}
