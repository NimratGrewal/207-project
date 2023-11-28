package use_case.toFeed;

import java.util.UUID;

public class FeedInputData {
    private final UUID dailyPromptID;
    public FeedInputData(UUID dailyPromptID) {
        this.dailyPromptID = dailyPromptID;
    }

    public UUID getDailyPromptID() {
        return dailyPromptID;
    }
}
