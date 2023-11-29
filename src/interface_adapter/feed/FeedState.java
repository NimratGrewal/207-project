package interface_adapter.feed;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedState {
    private LocalDate promptData;
    private String promptText;
    private Map<UUID, Map<String, Object>> responseInfoMap;

    public FeedState(FeedState copy) {
        promptData = copy.promptData;
        promptText = copy.promptText;
        responseInfoMap = copy.responseInfoMap;
    }
    public FeedState() {}

    public LocalDate getPromptDate() {
        return promptData;
    }

    public String getPromptText() {
        return promptText;
    }

    public Map<UUID, Map<String, Object>> getResponseInfoMap() {
        return responseInfoMap;
    }
    public void setPromptDate(LocalDate promptData) {
        this.promptData = promptData;
    }
    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }
    public void setResponseInfoMap(Map<UUID, Map<String, Object>> responseInfoMap) {
        this.responseInfoMap = responseInfoMap;
    }
}
