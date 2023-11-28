package entities;

import java.util.UUID;

public class Response {
    private final UUID responseId;
    private final UUID promptId;
    private final UUID userId;
    private final String songId;

    public Response(UUID responseId, UUID promptId, UUID userId, String songId) {
        this.responseId = responseId;
        this.promptId = promptId;
        this.userId = userId;
        this.songId = songId;
    }

    public UUID getResponseId() {
        return responseId;
    }

    public UUID getPromptId() {
        return promptId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getSongId() {
        return this.songId;
    }
}
