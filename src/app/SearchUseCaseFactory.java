package app;

import entities.SpotifyAPICaller;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_tracks.SearchTracksController;
import interface_adapter.search_tracks.SearchTracksPresenter;
import interface_adapter.search_tracks.SearchTracksViewModel;
import interface_adapter.set_response.SetResponseController;
import interface_adapter.set_response.SetResponsePresenter;

import interface_adapter.view_response.ViewResponseViewModel;
import use_case.search_tracks.SearchTracksInputBoundary;
import use_case.search_tracks.SearchTracksInteractor;
import use_case.search_tracks.SearchTracksOutputBoundary;


import use_case.set_response.SetResponseDataAccessInterface;
import use_case.set_response.SetResponseInputBoundary;
import use_case.set_response.SetResponseInteractor;
import use_case.set_response.SetResponseOutputBoundary;
import views.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    // search use case factory
    private SearchUseCaseFactory() {}

    public static SearchView create(
            SearchViewModel searchViewModel,
            SearchTracksViewModel searchTracksViewModel,
            ViewManagerModel viewManagerModel,
            SetResponseDataAccessInterface setResponseDataAccessInterface,
            SpotifyAPICaller apiCaller,
            ViewResponseViewModel viewResponseViewModel) {

        try {
            SearchTracksController searchTracksController = createSearchUseCase(searchTracksViewModel, apiCaller);

            SetResponseController setResponseController = createSetResponseUseCase(viewResponseViewModel,
                    searchViewModel, viewManagerModel, apiCaller, setResponseDataAccessInterface);

            return new SearchView(searchViewModel, searchTracksController, setResponseController,
                    searchTracksViewModel);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }


    private static SearchTracksController createSearchUseCase(
            SearchTracksViewModel searchTracksViewModel,
            SpotifyAPICaller apiCaller) throws IOException {

        SearchTracksOutputBoundary searchTracksPresenter = new SearchTracksPresenter(searchTracksViewModel);

        SearchTracksInputBoundary searchTracksInteractor = new SearchTracksInteractor(searchTracksPresenter, apiCaller);


        return new SearchTracksController(searchTracksInteractor);
    }

    private static SetResponseController createSetResponseUseCase(
            ViewResponseViewModel viewResponseViewModel,
            SearchViewModel searchViewModel,
            ViewManagerModel viewManagerModel,
            SpotifyAPICaller apiCaller,
            SetResponseDataAccessInterface setResponseDataAccessInterface) throws IOException {

        SetResponseOutputBoundary setResponsePresenter = new SetResponsePresenter(viewResponseViewModel, viewManagerModel);

        // interactor supposed to implement input boundary - typo in setResponseController
        SetResponseInputBoundary setResponseInteractor = new SetResponseInteractor(setResponseDataAccessInterface,
                setResponsePresenter, apiCaller);

        return new SetResponseController(setResponseInteractor);
    }



}
