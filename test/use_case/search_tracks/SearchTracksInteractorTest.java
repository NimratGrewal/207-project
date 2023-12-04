package use_case.search_tracks;

import entities.CommonUserFactory;
import entities.SpotifyAPICaller;
import entities.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTracksInteractorTest {

    SearchTracksOutputBoundary successPresenter = new SearchTracksOutputBoundary() {
        @Override
        public void prepareSuccessView(SearchTracksOutputData tracks) {
            assertTrue(tracks.getTrackList().size() <= 5);
        }
    };

    @Test
    void prepareSuccess() {
        final SpotifyAPICaller c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");

        SearchTracksInputBoundary sti = new SearchTracksInteractor(successPresenter, c);
        SearchTracksInputData stid = new SearchTracksInputData("greedy");
        sti.execute(stid);
    }
}