package entities;

import java.util.UUID;

public class Response {
    private final UUID responseId;
    private final UUID promptId;
    private final UUID userId;
    private final Song song;

    public Response(UUID promptId, UUID userId, Song song) {
        this.responseId = UUID.randomUUID();
        this.promptId = promptId;
        this.userId = userId;
        this.song = song;
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

    public Song getSong() {
        return this.song;
    }
}
