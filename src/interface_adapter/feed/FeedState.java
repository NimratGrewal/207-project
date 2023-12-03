package interface_adapter.feed;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedState {
    private LocalDate promptDate;
    private String promptText;
    private Map<UUID, Map<String, Object>> responseInfoMap;

    public FeedState(FeedState copy) {
        this.promptDate = copy.promptDate;
        this.promptText = copy.promptText;
        this.responseInfoMap = new HashMap<>();

        if (!copy.responseInfoMap.isEmpty()) {
            for (Map.Entry<UUID, Map<String, Object>> entry : copy.responseInfoMap.entrySet()) {
                UUID userId = entry.getKey();
                Map<String, Object> responseInfo = new HashMap<>(entry.getValue());
                this.responseInfoMap.put(userId, responseInfo);
            }
        }
    }

    public FeedState() {
        this.promptDate = LocalDate.now();
        this.promptText = "Default Prompt";
        this.responseInfoMap = new HashMap<>();
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

    public void setPromptDate(LocalDate promptDate) {
        this.promptDate = promptDate;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public void setResponseInfoMap(Map<UUID, Map<String, Object>> responseInfoMap) {
        this.responseInfoMap = responseInfoMap;
    }
}
