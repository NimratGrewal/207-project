package use_case.set_response;
public interface SetResponseOutputBoundary {
    void prepareSuccessView(SetResponseOutputData setResponseOutputData);

    void prepareFailView(String error);
}
