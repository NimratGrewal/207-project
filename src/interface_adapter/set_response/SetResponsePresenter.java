package interface_adapter.set_response;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_response.ViewResponseState;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.set_response.SetResponseOutputBoundary;
import use_case.set_response.SetResponseOutputData;

public class SetResponsePresenter implements SetResponseOutputBoundary {
    private ViewResponseViewModel viewResponseViewModel;
    private ViewManagerModel viewManagerModel;
    public SetResponsePresenter(ViewResponseViewModel viewResponseViewModel, ViewManagerModel viewManagerModel) {
        this.viewResponseViewModel = viewResponseViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(SetResponseOutputData setResponseOutputData) {

        ViewResponseState viewResponseState = viewResponseViewModel.getState();
        viewResponseState.setSongName(setResponseOutputData.getSongName());
        viewResponseState.setAlbumName(setResponseOutputData.getAlbumName());
        viewResponseState.setAlbumCover(setResponseOutputData.getAlbumArt());
        viewResponseState.setArtistNames(setResponseOutputData.getArtistNames());
        viewResponseState.setPromptText(setResponseOutputData.getPromptText());
        viewResponseViewModel.setState(viewResponseState);
        viewResponseViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewResponseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
