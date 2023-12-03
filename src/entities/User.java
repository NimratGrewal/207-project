package entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public interface User {
    String getUsername();

    String getPassword();

    UUID getUserId();

    LocalDateTime getCreationTime();

    HashMap<UUID, Response> getHistory();

    Response getResponse(UUID promptId);

    int getNumberOfResponses();

    void setResponse(UUID promptId, Response response);

    void deleteResponse(UUID promptId);

    boolean hasResponseForDailyPrompt(UUID dailyPromptId);
    Response getResponseForDailyPrompt(UUID promptId);
}
