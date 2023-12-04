package interface_adapter.search_tracks;

import interface_adapter.ViewManagerModel;
import use_case.search_tracks.SearchTracksOutputBoundary;
import use_case.search_tracks.SearchTracksOutputData;

public class SearchTracksPresenter implements SearchTracksOutputBoundary {
    private final SearchTracksViewModel searchTracksViewModel;
    //viewModel for confirm or cancel

    public SearchTracksPresenter(SearchTracksViewModel searchTracksViewModel) {
        this.searchTracksViewModel = searchTracksViewModel;
    }

    @Override
    public void prepareSuccessView(SearchTracksOutputData tracks) {
        SearchTracksState searchTracksState = searchTracksViewModel.getState();
        searchTracksState.setTracklist(tracks.getTrackList());
        searchTracksViewModel.setState(searchTracksState);
        searchTracksViewModel.firePropertyChanged();
    }
}
