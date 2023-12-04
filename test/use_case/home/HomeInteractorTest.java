package use_case.home;

import entities.CommonUser;
import entities.Prompt;
import entities.Response;
import entities.Song;
import interface_adapter.home.HomeController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeInteractorTest {

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
        // Creating a response for testing
        Response response = new Response(responseId, promptId, user.getUserId(), new Song("songId",
                "hips don't lie", artist, "some album", null));

        user.setResponse(promptId, response);

        assertEquals(response, user.getResponse(promptId));
        assertEquals(1, user.getNumberOfResponses());

        // used the mockito library for this test to see is prepareSuccessView ran
        // covers 100% of HomeTnteractor test with coverage

        HomeOutputBoundary mockPresenter = mock(HomeOutputBoundary.class);

        HomeDataAccessInterface homeDataAccessInterface = mock(HomeDataAccessInterface.class);

        when(homeDataAccessInterface.getLoggedInUser()).thenReturn(user);
        when(homeDataAccessInterface.responseExistsById(responseId)).thenReturn(true);
        when(homeDataAccessInterface.getCurrentPrompt()).thenReturn(hypotheticalPrompt);

        HomeInteractor interactor = new HomeInteractor(mockPresenter, homeDataAccessInterface);

        HomeController controller = new HomeController(interactor);

        // Call the method under test
        controller.execute();

        // Verify that the expected method was called on the mockPresenter
        verify(homeDataAccessInterface, times(1)).getCurrentPrompt();
        verify(homeDataAccessInterface, times(1)).getLoggedInUser();
        verify(homeDataAccessInterface, times(1)).responseExistsById(responseId);
        verify(homeDataAccessInterface).deleteResponse(responseId);
        verify(mockPresenter, times(1)).prepareSuccessView();
    }
}