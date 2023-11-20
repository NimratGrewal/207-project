package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;

public class HomePresenter implements HomeOutputBoundary {
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;


    public HomePresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        SearchState searchState = searchViewModel.getState();
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(promptViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
