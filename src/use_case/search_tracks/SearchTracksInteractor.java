package use_case.search_tracks;

import entities.Song;
import entities.SpotifyAPICaller;

import java.util.ArrayList;
import java.util.List;

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
        List<String[]> songsInfo = new ArrayList<>();
        for (Song s: trackList) {
            String[] songInfo = new String[4];
            songInfo[0] = s.getSongId();
            songInfo[1] = s.getName();
            songInfo[2] = String.join(", ", s.getArtists());
            songInfo[3] = s.getAlbum();
            songsInfo.add(songInfo);
        }

        SearchTracksOutputData searchTracksOutputData = new SearchTracksOutputData(songsInfo, false);
        searchTracksPresenter.prepareSuccessView(searchTracksOutputData);
    }
}
