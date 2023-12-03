package use_case.delete;

import data_access.DataAccessObjectFacade;
import entities.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeleteInteractorTest {

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

        DeleteResponseDataAccessInterface dataAccessInterface = new DeleteResponseDataAccessInterface() {
            @Override
            public boolean responseExistsById(UUID responseId) {
                return user.getResponse(promptId) != null;
            }

            @Override
            public void deleteResponse(UUID responseId) {
                if (user.getHistory().containsKey(promptId)) {
                    user.deleteResponse(promptId);
                }
            }

            @Override
            public User getLoggedinUser() {
                return user;
            }

            @Override
            public Response getResponseById(UUID userId, UUID responseId) {
                return response;
            }};


        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData deleteOutputData) {
                // check if the responseId has been removed from the user's history
                assertEquals(0, user.getNumberOfResponses());
                assertEquals(null, user.getResponse(promptId));

                assertFalse(dataAccessInterface.responseExistsById(responseId));

            }
        };

        DeleteInputData deleteInputData = new DeleteInputData(responseId);

        DeleteInputBoundary deleteInteractor = new DeleteInteractor(successPresenter, dataAccessInterface);

        deleteInteractor.execute(deleteInputData);

    }
}