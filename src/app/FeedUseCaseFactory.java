package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedViewModel;
import use_case.toFeed.FeedDataAccessInterface;
import use_case.toFeed.FeedInputBoundary;
import use_case.toFeed.FeedInteractor;
import use_case.toFeed.FeedOutputBoundary;
import views.FeedView;

import javax.swing.*;
import java.io.IOException;

public class FeedUseCaseFactory {
    private FeedUseCaseFactory() {}

    public static FeedView create(
            FeedViewModel feedViewModel) {

        return new FeedView(feedViewModel);
    }
}

