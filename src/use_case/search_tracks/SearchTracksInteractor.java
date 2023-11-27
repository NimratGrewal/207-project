package use_case.search_tracks;

import entities.Song;
import entities.SpotifyAPICaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTracksInteractor implements SearchTracksInputBoundary {
    final SearchTracksOutputBoundary searchTracksPresenter;
    final SpotifyAPICaller caller;

    public SearchTracksInteractor(SearchTracksOutputBoundary searchTracksPresenter, SpotifyAPICaller caller) {
        this.searchTracksPresenter = searchTracksPresenter;
        this.caller = caller;
    }

    @Override
    public void execute(SearchTracksInputData searchTracksInputData) {
        List<Song> trackList = caller.searchForTracks(searchTracksInputData.getQuery());
        List<Map<String, String>> songsInfo = new ArrayList<>();
        for (Song s: trackList) {
            Map<String, String> songInfo = new HashMap<>();
            songInfo.put("id", s.getSongId());
            songInfo.put("name", s.getName());
            songInfo.put("album", s.getAlbum());
            songInfo.put("artists", String.join(", ", s.getArtists()));
            songsInfo.add(songInfo);
        }

        SearchTracksOutputData searchTracksOutputData = new SearchTracksOutputData(songsInfo, false);
        searchTracksPresenter.prepareSuccessView(searchTracksOutputData);
    }
}
