package interface_adapter.login;

import entities.Prompt;
import entities.Song;
import entities.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseState;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    private final SearchViewModel searchViewModel;

    private final ViewResponseViewModel viewResponseViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginViewModel, SearchViewModel searchView, ViewResponseViewModel viewResponse, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.searchViewModel = searchView;
        this.viewResponseViewModel = viewResponse;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareLoggedInView(LoginOutputData user, Song song) {
        ViewResponseState viewResponseState = viewResponseViewModel.getState();
        viewResponseState.setSongName(song.getName());
        viewResponseState.setAlbumCover(song.getAlbumArt(10));
        String artists = String.join(",", song.getArtists());
        viewResponseState.setArtistNames(artists);
        viewResponseState.setAlbumName(song.getAlbum());
        this.viewResponseViewModel.setState(viewResponseState);
        this.viewResponseViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewResponseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void preparePromptView(LoginOutputData user, Prompt prompt) {
        SearchState searchState = searchViewModel.getState();
        searchState.setPromptText(prompt.getPromptText());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
