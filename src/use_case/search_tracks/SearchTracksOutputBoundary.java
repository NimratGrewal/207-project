package use_case.search_tracks;

public interface SearchTracksOutputBoundary {
    void prepareSuccessView(SearchTracksOutputData tracks);

    void prepareFailView(String error);
}
