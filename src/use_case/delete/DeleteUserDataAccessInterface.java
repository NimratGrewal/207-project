package use_case.delete;

import entities.Response;
import entities.User;

import java.util.UUID;

public interface DeleteUserDataAccessInterface {
    boolean responseExistsById(UUID responseId);

    void deleteResponse(UUID responseId, UUID promptId);

    User getLoggedinUser();

    Response getResponseById(UUID userId, UUID responseId);
}
