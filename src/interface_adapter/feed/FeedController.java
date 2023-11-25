package interface_adapter.feed;

import use_case.toFeed.FeedInputBoundary;
import use_case.toFeed.FeedInputData;

import java.util.UUID;

public class FeedController {
    private FeedInputBoundary feedInteractor;
    public FeedController(FeedInputBoundary feedInteractor) {
        this.feedInteractor = feedInteractor;
    }

    public void execute(UUID promptID) {
        FeedInputData inputData = new FeedInputData(promptID);
        feedInteractor.execute(inputData);
    }

}
