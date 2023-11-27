package use_case.search_tracks;

import java.util.List;
import java.util.Map;

public class SearchTracksOutputData {
    private final List<Map<String, String>> tracks;
    private boolean useCaseFailed;

    public SearchTracksOutputData(List<Map<String, String>> tracks, boolean useCaseFailed) {
        this.tracks = tracks;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Map<String, String>> getTrackList() {
        return tracks;
    }
}
