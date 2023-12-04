package use_case.set_response;

import entities.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SetResponseInteractorTest {
    SetResponseOutputBoundary successPresenter = new SetResponseOutputBoundary() {
        @Override
        public void prepareSuccessView(SetResponseOutputData setResponseOutputData) {
            assertEquals("Carly Rae Jepsen", setResponseOutputData.getArtistNames());
            assertEquals("Cut To The Feeling", setResponseOutputData.getAlbumName());
            assertEquals("Cut To The Feeling", setResponseOutputData.getSongName());
            assertEquals("What is your favourite song?", setResponseOutputData.getPromptText());
            assertNotNull(setResponseOutputData.getAlbumArt());
        }
    };

    @Test
    void successTest(){
        UserFactory uf = new CommonUserFactory();
        SpotifyAPICaller c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");

        User u1 = uf.create(UUID.fromString("78920e42-5a5b-45ad-93fe-4f4a93d61311"), "momo", "abc", LocalDateTime.parse("2007-12-03T10:15:30"));
        SetResponseDataAccessInterface dao = new SetResponseDataAccessInterface(){
            @Override
            public void setResponse(Response response) {
                u1.setResponse(getActivePromptId(), response);
            }

            @Override
            public String getActivePromptText() {
                return "What is your favourite song?";
            }

            @Override
            public UUID getActivePromptId() {
                return UUID.randomUUID();
            }

            @Override
            public User getLoggedInUser() {
                return u1;
            }
        };

        SetResponseOutputBoundary p = successPresenter;

        SetResponseInputBoundary sri = new SetResponseInteractor(dao, p, c);

        sri.execute(new SetResponseInputData("11dFghVXANMlKmJXsNCbNl"));
    }
}