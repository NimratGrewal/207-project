package use_case.delete;

import data_access.FileUserDataAccessObject;
import entities.*;
import interface_adapter.delete.DeletePresenter;
import use_case.delete.DeleteUserDataAccessInterface;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class DeleteInteractorTest {
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

        DeleteResponseDataAccessInterface responseDAO = new DeleteResponseDataAccessInterface() {
            private final Map<UUID, List<UUID>> responses = new LinkedHashMap<>();

            @Override
            public boolean responseexistsById(UUID responseId) {
                for (List<UUID> responseList : responses.values()) {
                    for (UUID response : responseList) {
                        if (responseId.equals(response)) {
                            return true;
                        }
                    }
                }
                return false;
            }


            @Override
            public void deleteResponse(UUID responseId) {
                for (Map.Entry<UUID, List<UUID>> entry : responses.entrySet()) {
                    List<UUID> responseList = entry.getValue();
                    if (responseList.contains(responseId)) {
                        responseList.remove(responseId);
                        break;
                    }

                }
            }
        }
            ;
            DeleteUserDataAccessInterface userDAO = new DeleteUserDataAccessInterface() {
                private final Map<UUID, User> users = new HashMap<>();

                // add the user info here- > add response for user.

                @Override
                public boolean responseExistsById(UUID responseId) {
                    for (User user : users.values()) {
                        if (user.getHistory().containsKey(responseId)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void deleteResponse(UUID responseId, UUID promptId) {
                    for (User user : users.values()) {
                        if (user.getHistory().containsKey(promptId)) {
                            user.deleteResponse(promptId);
                        }
                    }

                }

                @Override
                public User getLoggedinUser() {
                    return user;
                }

                @Override
                public Response getResponseById(UUID userId, UUID responseId) {
                    return response;
                }
            };


            DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
                @Override
                public void prepareSuccessView(DeleteOutputData deleteOutputData) {
                    // check if the responseId has been removed from both of the DAO's and the user's history
                    assertEquals(0, user.getNumberOfResponses());
                    assertEquals("prompt does not exist", user.getResponse(promptId));

                    assertFalse(responseDAO.responseexistsById(responseId));
                    assertFalse(userDAO.responseExistsById(responseId));

                }
            };

            DeleteInputData deleteInputData = new DeleteInputData(responseId);

            DeleteInputBoundary deleteInteractor = new DeleteInteractor(successPresenter, responseDAO,
                    userDAO);

            deleteInteractor.execute(deleteInputData);


    }
}