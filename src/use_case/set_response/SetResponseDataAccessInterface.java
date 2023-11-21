package use_case.set_response;

import entities.Response;
import entities.Song;

import java.util.UUID;

public interface SetResponseDataAccessInterface {
    public void setResponse(Response response);

    public String getActivePromptText();

    public UUID getActivePromptId();

    public UUID getLoggedInUserId();
}
