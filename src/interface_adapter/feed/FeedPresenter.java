package interface_adapter.feed;

import use_case.toFeed.FeedOutputBoundary;
import use_case.toFeed.FeedOutputData;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FeedPresenter implements FeedOutputBoundary {
    private final FeedViewModel feedViewModel;

    public FeedPresenter(FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void present(FeedOutputData outputData) {
        LocalDate promptDate = outputData.getPromptDate();
        String promptText = outputData.getPromptText();
        List<UUID> promptResponses = outputData.getPromptResponses();

        FeedState feedState = new FeedState(promptDate, promptText, promptResponses);
        feedViewModel.setState(feedState);
    }
}
