package use_case.search_tracks;

public class SearchTracksInputData {
    private final String query;

    public SearchTracksInputData(String query) {
        this.query = query;
    }

    public String getQuery() { return query; }
}
