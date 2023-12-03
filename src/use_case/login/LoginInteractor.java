package use_case.login;

import data_access.FileUserDataAccessObject;
import entities.User;
import entities.Prompt;

import java.io.File;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
//        String username = loginInputData.getUsername();
//        String password = loginInputData.getPassword();
//        if (!userDataAccessObject.existsByName(username)) {
//            loginPresenter.prepareFailView(username + ": Account does not exist.");
//        } else {
//            String pwd = userDataAccessObject.get(username).getPassword();
//            if (!password.equals(pwd)) {
//                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
//            } else {
//
//                User user = userDataAccessObject.get(loginInputData.getUsername());
//
//                ((FileUserDataAccessObject) userDataAccessObject).setLoggedInUser(user);
//
//                Prompt prompt = userDataAccessObject.getCurrentPrompt();
//                if(user.getHistory().containsKey(prompt.getPromptId())){
//                    LoginOutputData promptOutputData = new LoginOutputData(user.getUsername(), false);
//                    loginPresenter.preparePromptView(promptOutputData, prompt);
//                }
//                else{
//                    LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), true);
//                    loginPresenter.prepareLoggedInView(loginOutputData);
//                }
//            }
//        }
    }
}