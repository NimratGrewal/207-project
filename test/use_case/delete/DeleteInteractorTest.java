package use_case.delete;

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
                if (user.getResponse(promptId) != null) {
                    return user.getResponse(promptId);
                }
                return null;
            }};


        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData deleteOutputData) {
                // check if the responseId has been removed from the user's history
                // assertEquals(0, user.getNumberOfResponses());
                // assertEquals(null, user.getResponse(promptId));
                assertFalse(dataAccessInterface.responseExistsById(deleteOutputData.getResponseId()));
                assertNull(dataAccessInterface.getResponseById(dataAccessInterface.getLoggedinUser().getUserId(),
                        deleteOutputData.getResponseId()));

            }
        };

        DeleteInputData deleteInputData = new DeleteInputData(responseId);

        DeleteInputBoundary deleteInteractor = new DeleteInteractor(successPresenter, dataAccessInterface);

        deleteInteractor.execute(deleteInputData);

//        // Mocking the dependencies
//        DataAccessObjectFacade dataAccessObjectFacade = mock(DataAccessObjectFacade.class); // Corrected to mock
//
//        // Mocking the presenter
//        DeleteOutputBoundary deletePresenter = mock(DeleteOutputBoundary.class);
//
//        UUID userId = UUID.randomUUID();
//        UUID responseId = UUID.randomUUID();
//        UUID promptId = UUID.randomUUID();
//        List<String> artist = new ArrayList<>();
//        String promptQuestion = "Best Shakira Song?";
//        LocalDate promptDate = LocalDate.now();
//        Prompt hypotheticalPrompt = new Prompt(promptQuestion, promptDate, promptId);
//        artist.add("Shakira");
//        User loggedInUser = new CommonUser(userId, "Nimrat", "password", LocalDateTime.now());
//        // Assuming a User object with an ID
//        Response response = new Response(responseId, promptId, userId, new Song("songId",
//                "hips don't lie", artist, "some album", null)); // Assuming a Response object with necessary parameters
//
//        loggedInUser.setResponse(promptId, response);
//
//        assertEquals(response, loggedInUser.getResponse(promptId));
//
//        // Setting up mock behaviors
//        when(dataAccessObjectFacade.getLoggedinUser()).thenReturn(loggedInUser);
//        when(dataAccessObjectFacade.getResponseById(userId, responseId)).thenReturn(response);
//        when(dataAccessObjectFacade.responseExistsById(responseId)).thenReturn(true);
//        // doNothing().when(dataAccessObjectFacade).deleteResponse(responseId);
//
//
//
//        // Initializing the interactor
//        DeleteInteractor deleteInteractor = new DeleteInteractor(deletePresenter, dataAccessObjectFacade);
//
//        // Creating the input data for delete operation
//        DeleteInputData deleteInputData = new DeleteInputData(responseId);
//
//        // Executing the delete operation
//        deleteInteractor.execute(deleteInputData);
//
//        // Verifying expected interactions
//        verify(dataAccessObjectFacade).getLoggedinUser();
//        verify(dataAccessObjectFacade).getResponseById(userId, responseId);
//        verify(dataAccessObjectFacade).responseExistsById(responseId);
//
//        verify(dataAccessObjectFacade).deleteResponse(responseId);
//        verify(deletePresenter).prepareSuccessView(any(DeleteOutputData.class));
//
//        // Assert that the response was removed from the user's history
//
//        // assertNull(loggedInUser.getResponse(promptId));
    }

    }
