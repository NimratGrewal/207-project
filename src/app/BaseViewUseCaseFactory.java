package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.prompt.PromptController;
import interface_adapter.prompt.PromptPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.toFeed.FeedInputBoundary;
import use_case.toFeed.FeedInteractor;
import use_case.toFeed.FeedOutputBoundary;
import use_case.to_prompt.PromptDataAccessInterface;
import use_case.to_prompt.PromptInputBoundary;
import use_case.to_prompt.PromptInteractor;
import use_case.to_prompt.PromptOutputBoundary;
import views.BaseView;

public class BaseViewUseCaseFactory {
    public BaseViewUseCaseFactory() {}

    public static BaseView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ViewResponseViewModel viewResponseViewModel,
            PromptDataAccessInterface promptDataAccessInterface,
            FeedViewModel feedViewModel
    ) {
        PromptController promptController = createPromptUseCase(viewResponseViewModel, searchViewModel, viewManagerModel, promptDataAccessInterface);
        return null;
    }

    public static PromptController createPromptUseCase(ViewResponseViewModel viewResponseViewModel,
                                                       SearchViewModel searchViewModel,
                                                       ViewManagerModel viewManagerModel,
                                                       PromptDataAccessInterface promptDataAccessInterface) {
        PromptOutputBoundary promptPresenter = new PromptPresenter(viewResponseViewModel, searchViewModel, viewManagerModel);
        PromptInputBoundary promptInteractor = new PromptInteractor(promptDataAccessInterface, promptPresenter);
        return new PromptController(promptInteractor);
    }
}
