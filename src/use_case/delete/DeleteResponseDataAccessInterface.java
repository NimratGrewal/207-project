package use_case.delete;

import entities.Response;
import entities.User;

import java.util.UUID;

public interface DeleteResponseDataAccessInterface {

    boolean responseExistsById(UUID responseId);

    void deleteResponse(UUID responseId);

    User getLoggedinUser();

    Response getResponseById(UUID userId, UUID responseId);


}
