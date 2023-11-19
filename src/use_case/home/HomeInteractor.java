package use_case.home;

public class HomeInteractor implements HomeInputBoundary{

    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeOutputBoundary homePresenter) {
        this.homePresenter = homePresenter;
    }

    public void execute() {
        homePresenter.prepareSuccessView();
    }
}
