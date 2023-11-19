package use_case.search_tracks;

import java.util.List;

public class SearchTracksOutputData {
    private final List<String[]> tracks;
    private boolean useCaseFailed;

    public SearchTracksOutputData(List<String[]> tracks, boolean useCaseFailed) {
        this.tracks = tracks;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String[]> getTrackList() {
        return tracks;
    }
}
