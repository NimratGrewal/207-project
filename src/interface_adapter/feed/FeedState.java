package interface_adapter.feed;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FeedState {
    private final LocalDate promptDate;
    private final String promptText;
    private final List<UUID> promptResponses;

    public FeedState(LocalDate promptDate, String promptText, List<UUID> promptResponses) {
        this.promptDate = promptDate;
        this.promptText = promptText;
        this.promptResponses = promptResponses;
    }

    public LocalDate getPromptDate() {
        return promptDate;
    }

    public String getPromptText() {
        return promptText;
    }

    public List<UUID> getPromptResponses() {
        return promptResponses;
    }
}
