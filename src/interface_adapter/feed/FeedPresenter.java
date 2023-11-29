package interface_adapter.feed;

import interface_adapter.ViewManagerModel;
import use_case.toFeed.FeedOutputBoundary;
import use_case.toFeed.FeedOutputData;
import views.ViewManager;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class FeedPresenter implements FeedOutputBoundary {
    private final FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;

    public FeedPresenter(ViewManagerModel viewManagerModel, FeedViewModel feedViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void present(FeedOutputData outputData) {

        FeedState feedState = feedViewModel.getState();
        feedState.setPromptDate(outputData.getPromptDate());
        feedState.setPromptText(outputData.getPromptText());
        feedState.setResponseInfoMap(outputData.getResponseInfoMap());

        this.feedViewModel.setState(feedState);
        this.feedViewModel.firePropertyChanged();;

        this.viewManagerModel.setActiveView(feedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
