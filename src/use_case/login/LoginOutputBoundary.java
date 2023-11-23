package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void preparePromptView(LoginOutputData user);
    void prepareFailView(String error);

}
