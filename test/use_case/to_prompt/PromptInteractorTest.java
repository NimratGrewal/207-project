package use_case.to_prompt;

import entities.*;
import interface_adapter.prompt.PromptController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PromptInteractorTest {

    PromptOutputBoundary promptOutputBoundary = new PromptOutputBoundary() {
        @Override
        public void prepareSearchView(String promptText) {
            assertEquals(promptText, "What?");
        }

        @Override
        public void prepareViewResponseView(PromptOutputData promptOutputData) {
            assertEquals("What?", promptOutputData.getPromptText());
            assertEquals("Cut To The Feeling", promptOutputData.getAlbumName());
            assertEquals("Cut To The Feeling", promptOutputData.getSongName());
            assertEquals("Carly Rae Jepsen", promptOutputData.getArtistNames());
            assertNotNull(promptOutputData.getAlbumArt());
        }
    };

    @Test
    public void searchTest() {
        PromptDataAccessInterface promptDataAccessInterface = new PromptDataAccessInterface() {
            final UserFactory uf = new CommonUserFactory();
            final SpotifyAPICaller c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");
            User u1 = uf.create(UUID.fromString("78920e42-5a5b-45ad-93fe-4f4a93d61311"), "momo", "abc", LocalDateTime.parse("2007-12-03T10:15:30"));
            Prompt p1 = new Prompt("What?", LocalDate.now(), UUID.randomUUID());
            Response r1 = new Response(p1.getPromptId(), u1.getUserId(), c.getTrack("11dFghVXANMlKmJXsNCbNl"));

            @Override
            public User getLoggedInUser() {
                u1.setResponse(p1.getPromptId(), r1);
                return u1;
            }

            @Override
            public Prompt getActivePrompt() {
                return p1;
            }

            @Override
            public Response getLoggedInUserResponse() {
                return r1;
            }
        };

        PromptInputBoundary promptInteractor = new PromptInteractor(promptDataAccessInterface, promptOutputBoundary);
        PromptController promptController = new PromptController(promptInteractor);
        promptController.execute();
    }

    @Test
    public void setResponseTest() {

        PromptDataAccessInterface promptDataAccessInterface = new PromptDataAccessInterface() {
            final UserFactory uf = new CommonUserFactory();
            final SpotifyAPICaller c = new SpotifyAPICaller("216b6438ba554128ade0b63afa48ddd8", "56e33aff04bc4aa28dc3d9e54bb231cd");
            User u1 = uf.create(UUID.fromString("78920e42-5a5b-45ad-93fe-4f4a93d61311"), "momo", "abc", LocalDateTime.parse("2007-12-03T10:15:30"));
            Prompt p1 = new Prompt("What?", LocalDate.now(), UUID.randomUUID());

            @Override
            public User getLoggedInUser() {
                return u1;
            }

            @Override
            public Prompt getActivePrompt() {
                return p1;
            }

            @Override
            public Response getLoggedInUserResponse() {
                return null;
            }
        };

        PromptInputBoundary promptInteractor = new PromptInteractor(promptDataAccessInterface, promptOutputBoundary);
        PromptController promptController = new PromptController(promptInteractor);
        promptController.execute();
    }
}