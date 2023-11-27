package use_case.toFeed;

import java.util.UUID;

public class FeedInputData {
    private final UUID promptID;
    public FeedInputData(UUID promptID) {
        this.promptID = promptID;
    }

    public UUID getPromptID() {
        return promptID;
    }
}
