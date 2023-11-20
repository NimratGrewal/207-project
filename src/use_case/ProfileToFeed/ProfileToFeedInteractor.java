package use_case.ProfileToFeed;

public class ProfileToFeedInteractor implements ProfileToFeedInputBoundary{
    private final ProfileToFeedOutputBoundary outputBoundary;

    public ProfileToFeedInteractor(ProfileToFeedOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void toFeed() {

    }
}
