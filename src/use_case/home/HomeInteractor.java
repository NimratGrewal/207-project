package use_case.home;

public class HomeInteractor implements HomeInputBoundary{

    final HomeOutputBoundary homePresenter;
    private final HomeDataAccessInterface homeDataAccessInterface;

    public HomeInteractor(HomeOutputBoundary homePresenter, HomeDataAccessInterface homeDataAccessInterface) {
        this.homePresenter = homePresenter;
        this.homeDataAccessInterface = homeDataAccessInterface;
    }

    public void execute() {
        homeDataAccessInterface.deleteLoggedInUserResponse();
        homePresenter.prepareSuccessView();
    }
}
