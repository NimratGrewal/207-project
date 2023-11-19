package interface_adapter.search_tracks;

import java.util.ArrayList;
import java.util.List;

public class SearchTracksState {
    private List<String[]> trackList = new ArrayList<>();

    public SearchTracksState(SearchTracksState copy) {
        trackList = copy.getTrackList();
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchTracksState() {}

    public List<String[]> getTrackList() { return trackList; }
    public void setTracklist(List<String[]> trackList) { this.trackList = trackList; }
}
