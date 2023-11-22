package interface_adapter.feed;

//import interface_adapter.ViewManagerModel;
//import interface_adapter.profile.ProfileViewModel;
//import use_case.ProfileToFeed.ProfileToFeedOutputBoundary;
//
//public class FeedPresenter implements ProfileToFeedOutputBoundary{
//    private final ProfileViewModel profileViewModel;
//    private final FeedViewModel feedViewModel;
//    private ViewManagerModel viewManagerModel;
//
//    public FeedPresenter(ProfileViewModel profileViewModel, FeedViewModel feedViewModel,
//                         ViewManagerModel viewManagerModel) {
//        this.profileViewModel = profileViewModel;
//        this.feedViewModel = feedViewModel;
//        this.viewManagerModel = viewManagerModel;
//    }
//
//    public void toFeed() {
//        FeedState feedState = feedViewModel.getState();
//        this.feedViewModel.setState(feedState);
//        this.feedViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setActiveView(feedState.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//    }
//
//}
