package interface_adapter.feed;

import entities.Prompt;
import entities.Response;
import use_case.toFeed.FeedOutputBoundary;
import use_case.toFeed.FeedOutputData;

import java.time.LocalDate;
import java.util.List;

public class FeedPresenter implements FeedOutputBoundary {
    private final FeedViewModel feedViewModel;
    public FeedPresenter(FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
    }
    @Override
    public void present(FeedOutputData outputData) {
        LocalDate promptDate = outputData.getPromptDate();
        Prompt promptOfTheDay = outputData.getPromptOfTheDay();
        List<Response> promptResponses = outputData.getPromptResponses();

        FeedState feedState = new FeedState(promptDate, promptOfTheDay, promptResponses);
        feedViewModel.setState(feedState);
    }
}
