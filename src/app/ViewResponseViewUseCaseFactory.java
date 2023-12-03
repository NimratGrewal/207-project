package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.home.HomeDataAccessInterface;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import views.ViewResponseView;

import javax.swing.*;
import java.io.IOException;

public class ViewResponseViewUseCaseFactory {
    // reset response use case factory
    private ViewResponseViewUseCaseFactory() {
    }

    public static ViewResponseView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
            ViewResponseViewModel viewResponseViewModel, HomeDataAccessInterface homeDataAccessInterface) {
        try {
            HomeController homeController = createResetUseCase(viewManagerModel, searchViewModel, homeDataAccessInterface);
            return new ViewResponseView(viewResponseViewModel, homeController);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "something went wrong");
        }
        return null;
    }

        public static HomeController createResetUseCase(
                ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, HomeDataAccessInterface homeDataAccessInterface) throws IOException {

            HomeOutputBoundary homeOutputBoundary = new HomePresenter(searchViewModel, viewManagerModel);
            HomeInputBoundary homeInteractor = new HomeInteractor(homeOutputBoundary, homeDataAccessInterface);
            return new HomeController(homeInteractor);
        }
}
