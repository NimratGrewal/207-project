package use_case.FeedToProfile;

public class FeedtoProfileInteractor implements FeedToProfileInputBoundary{
    private final FeedToProfileOutputBoundary outputBoundary;
    public FeedtoProfileInteractor(FeedToProfileOutputBoundary outputBoundary){
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void toProfile() {

    }
}
