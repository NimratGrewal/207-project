package interface_adapter.search_tracks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchTracksState {
    private List<Map<String, String>> trackList = new ArrayList<>();

    public SearchTracksState(SearchTracksState copy) {
        trackList = copy.getTrackList();
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchTracksState() {}

    public List<Map<String, String>> getTrackList() { return trackList; }
    public void setTracklist(List<Map<String, String>> trackList) { this.trackList = trackList; }
}
