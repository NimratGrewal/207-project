package use_case.login;

import entities.Prompt;
import entities.User;

public interface LoginOutputBoundary {
    void prepareLoggedInView(LoginOutputData user);


    void prepareFailView(String error);

    void preparePromptView(LoginOutputData user, Prompt prompt);
}
