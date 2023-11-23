package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import views.HomeView;
import views.PromptView;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final PromptView promptView;

    private final HomeView homeView;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginViewModel, PromptView promptView, HomeView homeView, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.promptView = promptView;
        this.homeView = homeView;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(LoginOutputData user) {

    }

    @Override
    public void preparePromptView(LoginOutputData user) {
        //add in prompt view
        PromptState promptState = promptView.getState();
        promptState.setQuestion(prompt);
        this.promptView.setState(promptState);
        this.promptView.firePropertyChanged();

        this.viewManagerModel.setActiveView(promptView.getName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
