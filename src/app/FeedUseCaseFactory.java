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
            ViewManagerModel viewManagerModel,
            FeedViewModel feedViewModel,
            FeedDataAccessInterface feedDataAccessObject) {

        try {
            FeedController feedController = createFeedController(
                    viewManagerModel,
                    feedViewModel,
                    feedDataAccessObject
            );

            return new FeedView(feedViewModel, feedController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not create FeedView!");
        }

        return null;
    }

    private static FeedController createFeedController(
            ViewManagerModel viewManagerModel,
            FeedViewModel feedViewModel,
            FeedDataAccessInterface feedDataAccessObject) throws IOException {

        FeedOutputBoundary feedPresenter = new FeedPresenter(viewManagerModel, feedViewModel);
        FeedInputBoundary feedInteractor = new FeedInteractor(feedDataAccessObject, feedPresenter);

        return new FeedController(feedInteractor);
    }

}

