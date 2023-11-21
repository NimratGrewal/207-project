package interface_adapter.delete;

import java.util.UUID;

public class DeleteState {
    private UUID responseId;

    public DeleteState(DeleteState copy) {
        responseId = copy.responseId;
    }
    public DeleteState () {}

    public UUID getResponseId() {return responseId;}

    public void setResponseId(UUID responseId) {this.responseId = responseId; }

}
