package use_case.login;

import entities.User;
import entities.Prompt;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    final PromptDataAccessInterface promptDataAccessObject;

    public LoginInteractor(LoginUserDataInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, PromptDataAccessInterface promptDataAccessInterface
                           ) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.promptDataAccessObject = promptDataAccessInterface;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());
                Prompt prompt = promptDataAccessObject.getCurrentPrompt();
                if(user.getHistory().containsKey(prompt.getPromptId())){
                    LoginOutputData promptOutputData = new LoginOutputData(user.getUsername(), false);
                    loginPresenter.preparePromptView(promptOutputData, prompt);
                }
                else{
                    LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), true);
                    loginPresenter.prepareLoggedInView(loginOutputData);
                }
            }
        }
    }
}