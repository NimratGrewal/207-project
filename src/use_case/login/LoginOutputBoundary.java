package use_case.login;

import entities.Prompt;
import entities.Song;
import entities.User;

public interface LoginOutputBoundary {
    void prepareLoggedInView(LoginOutputData user, Song song);


    void prepareFailView(String error);

    void preparePromptView(LoginOutputData user, Prompt prompt);
}
