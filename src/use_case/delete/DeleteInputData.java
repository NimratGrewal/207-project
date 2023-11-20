package use_case.delete;

import java.util.UUID;

public class DeleteInputData {

    final private UUID promptId;

    public DeleteInputData(UUID promptId) {
        this.promptId = promptId;
    }

    UUID getPromptId() {return this.promptId;}
}
