package interface_adapter.feed;

import use_case.toFeed.FeedOutputBoundary;
import use_case.toFeed.FeedOutputData;

import java.time.LocalDate;
import java.util.Map;
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
        Map<UUID, Map<String, Object>> responseInfoMap = outputData.getResponseInfoMap();

        FeedState feedState = new FeedState(promptDate, promptText, responseInfoMap);
        feedViewModel.setState(feedState);
    }
}
