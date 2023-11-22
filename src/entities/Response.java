package entities;

import java.util.UUID;

public class Response {
    private final UUID responseId;
    private final UUID promptId;
    private final User user;
    private final Song song;

    public Response(UUID responseId, UUID promptId, User user, Song song) {
        this.responseId = responseId;
        this.promptId = promptId;
        this.user = user;
        this.song = song;
    }

    public UUID getResponseId() {
        return responseId;
    }

    public UUID getPromptId() {
        return promptId;
    }

    public User getUser() {
        return user;
    }

    public Song getSong() {
        return this.song;
    }
}
