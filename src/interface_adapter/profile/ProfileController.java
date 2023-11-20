package interface_adapter.profile;

import use_case.FeedToProfile.FeedToProfileInputBoundary;

public class ProfileController implements FeedToProfileInputBoundary{
    private final FeedToProfileInputBoundary profileInteractor;

    public ProfileController(FeedToProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void toProfile() {

    }
}
