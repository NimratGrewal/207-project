package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.search.SearchViewModel;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;

import java.io.IOException;

public class ViewResponseViewUseCaseFactory {
    // reset response use case factory
    private ViewResponseViewUseCaseFactory() {}

    public static HomeController createResetUseCase(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) throws IOException {
        HomeOutputBoundary homeOutputBoundary = new HomePresenter(searchViewModel, viewManagerModel);
        HomeInputBoundary homeInteractor = new HomeInteractor(homeOutputBoundary);
        return new HomeController(homeInteractor);
    }

    )

}
