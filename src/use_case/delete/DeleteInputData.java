package use_case.delete;

import java.util.UUID;

public class DeleteInputData {
    final private UUID responseId;

    public DeleteInputData(UUID responseId) {
        this.responseId = responseId;
    }

    UUID getResponseId() {return this.responseId;}
}
