package interface_adapter.prompt;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseState;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.to_prompt.PromptOutputBoundary;
import use_case.to_prompt.PromptOutputData;

public class PromptPresenter implements PromptOutputBoundary {
    private final ViewResponseViewModel viewResponseViewModel;
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public PromptPresenter(ViewResponseViewModel viewResponseViewModel, SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.viewResponseViewModel = viewResponseViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSearchView(String promptText) {
        SearchState searchState = searchViewModel.getState();
        searchState.setPromptText(promptText);
        searchState.setSearchBarText("");
        searchViewModel.setState(searchState);

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareViewResponseView(PromptOutputData promptOutputData) {
        ViewResponseState viewResponseState = viewResponseViewModel.getState();
        viewResponseState.setSongName(promptOutputData.getSongName());
        viewResponseState.setAlbumName(promptOutputData.getAlbumName());
        viewResponseState.setAlbumCover(promptOutputData.getAlbumArt());
        viewResponseState.setArtistNames(promptOutputData.getArtistNames());
        viewResponseState.setPromptText(promptOutputData.getPromptText());
        viewResponseViewModel.setState(viewResponseState);
        viewResponseViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewResponseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
