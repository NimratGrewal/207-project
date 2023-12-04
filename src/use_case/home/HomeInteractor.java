package use_case.home;

import entities.CommonUser;
import entities.Response;
import entities.User;
import entities.Prompt;

public class HomeInteractor implements HomeInputBoundary{

    final HomeOutputBoundary homePresenter;
    final HomeDataAccessInterface homeDataAccessInterface;

    public HomeInteractor(HomeOutputBoundary homePresenter, HomeDataAccessInterface homeDataAccessInterface) {
        this.homePresenter = homePresenter;
        this.homeDataAccessInterface = homeDataAccessInterface;
    }

    public void execute() {

        User user = homeDataAccessInterface.getLoggedInUser();
        Prompt currPrompt = homeDataAccessInterface.getCurrentPrompt();
        Response response = user.getResponse(currPrompt.getPromptId());

        // delete the response from the prompt
        if (homeDataAccessInterface.responseExistsById(response.getResponseId())) {

            homeDataAccessInterface.deleteResponse(response.getResponseId());

        }

        homePresenter.prepareSuccessView();
    }
}
