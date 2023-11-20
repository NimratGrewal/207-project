package use_case.signup;

public interface SignupOutputBoundary {
    void PrepareSuccessView(SignupOutputData user);

    void PrepareFailView(String error);
}
