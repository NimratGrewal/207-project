package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import use_case.toProfile.ProfileOutputBoundary;
import use_case.toProfile.ProfileOutputData;

import java.util.Map;
import java.util.UUID;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;
    private ViewManagerModel viewManagerModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void present(ProfileOutputData outputData) {

        ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(outputData.getUsername());
        profileState.setNumberOfResponses(outputData.getNumberOfResponses());
        profileState.setResponseInfoMap(outputData.getResponseInfoMap());

        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

