package use_case.delete;

import java.util.UUID;

public class DeleteOutputData {

    private final UUID responseId;
    public DeleteOutputData(UUID responseId) {
        this.responseId = responseId;
    }
    public UUID getResponseId() {return this.responseId;}
}
