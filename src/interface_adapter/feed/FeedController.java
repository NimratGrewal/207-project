package interface_adapter.feed;

import use_case.ProfileToFeed.ProfileToFeedInputBoundary;

public class FeedController implements ProfileToFeedInputBoundary {
    private final ProfileToFeedInputBoundary feedInteractor;

    public FeedController(ProfileToFeedInputBoundary feedInteractor) {
        this.feedInteractor = feedInteractor;
    }
    public void toFeed() {
        feedInteractor.toFeed();
    }
}
