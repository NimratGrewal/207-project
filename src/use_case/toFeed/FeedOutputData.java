package use_case.toFeed;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedOutputData {
    private final LocalDate promptDate;
    private final String promptText;
    private final Map<UUID, Map<String, Object>> responseInfoMap;

    public FeedOutputData(LocalDate promptDate, String promptText, Map<UUID, Map<String, Object>> responseInfoMap) {
        this.promptDate = promptDate;
        this.promptText = promptText;
        this.responseInfoMap = responseInfoMap;
    }

    public LocalDate getPromptDate() {
        return promptDate;
    }

    public String getPromptText() {
        return promptText;
    }

    public Map<UUID, Map<String, Object>> getResponseInfoMap() {
        return responseInfoMap;
    }
}
