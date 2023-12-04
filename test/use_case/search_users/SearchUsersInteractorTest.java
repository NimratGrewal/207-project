package use_case.search_users;

import data_access.FileUserDataAccessObject;
import entities.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SearchUsersInteractorTest {
    private FileUserDataAccessObject fileUserDataAccessMock;
    private Song song;

    SearchUsersOutputBoundary searchUsersPresenter = new SearchUsersOutputBoundary() {
        @Override
        public void prepareUserView(SearchUsersOutputData usersOutputData) {
            assertEquals("Nimrat", usersOutputData.getUsername());
            assertEquals(1, usersOutputData.getNumberOfResponses());
        }

        @Override
        public void prepareFailView(String error) {

        }

        @Override
        public void prepareSearchView() {

        }
    };

    @Test
    void execute() {
        CommonUser user = new CommonUser("Nimrat", "password");

        UUID promptId = UUID.randomUUID();
        UUID responseId = UUID.randomUUID();

        List<String> artist = new ArrayList<>();
        String promptQuestion = "Best Shakira Song?";
        LocalDate promptDate = LocalDate.now();
        Prompt hypotheticalPrompt = new Prompt(promptQuestion, promptDate, promptId);
        artist.add("Shakira");
        String songId = "1234";
        String name = "Example Song";
        List<String> artists = new ArrayList<>();
        artists.add("Artist 1");
        artists.add("Artist 2");
        String album = "Example Album";
        Image albumArt = new ImageIcon("path/to/album/image.png").getImage();

        song = new Song(songId, name, artists, album, albumArt);
        Response response = new Response(responseId, promptId, song);
        user.setResponse(promptId, response);

        SearchUsersDataAccessInterface searchUsersDataAccessInterface= new SearchUsersDataAccessInterface() {
            @Override
            public boolean usernameExists(String username) {
                return true;
            }

            @Override
            public User getUsername(String username) {
                return user;
            }

            @Override
            public Prompt getPromptById(UUID promptId) {
                return hypotheticalPrompt;
            }
        };

        SearchUsersOutputBoundary presenter = searchUsersPresenter;

        SearchUsersInputBoundary interactor = new SearchUsersInteractor(searchUsersDataAccessInterface, presenter);

        interactor.execute(new SearchUsersInputData("nimrat"));
    }

    @Test
    void profileToSearch() {
    }
}