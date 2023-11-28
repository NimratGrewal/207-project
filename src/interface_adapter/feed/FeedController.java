package interface_adapter.feed;

import use_case.toFeed.FeedInputBoundary;
import use_case.toFeed.FeedInputData;

import java.util.UUID;

public class FeedController {
    private final FeedInputBoundary feedInteractor;

    public FeedController(FeedInputBoundary feedInteractor) {
        this.feedInteractor = feedInteractor;
    }

    public void execute(UUID dailyPromptId) {
        FeedInputData inputData = new FeedInputData(dailyPromptId);
        feedInteractor.execute(inputData);
    }

}
