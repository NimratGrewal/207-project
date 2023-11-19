package interface_adapter.search_tracks;

import use_case.search_tracks.SearchTracksInputBoundary;
import use_case.search_tracks.SearchTracksInputData;

public class SearchTracksController {
    private final SearchTracksInputBoundary searchTracksInteractor;

    public SearchTracksController(SearchTracksInputBoundary searchTracksInteractor) {
        this.searchTracksInteractor = searchTracksInteractor;
    }

    public void execute(String query) {
        SearchTracksInputData searchTracksInputData = new SearchTracksInputData(query);
        searchTracksInteractor.execute(searchTracksInputData);
    }
}
