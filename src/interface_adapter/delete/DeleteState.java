package interface_adapter.delete;

import java.util.UUID;

public class DeleteState {
    private UUID responseId;
    private String responseError;

    public DeleteState(DeleteState copy) {
        responseId = copy.responseId;
        responseError = copy.responseError;
    }
    public DeleteState () {}

    public UUID getResponseId() {return responseId;}

    public void setResponseId(UUID responseId) {this.responseId = responseId; }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }

    public String getResponseError() {
        return responseError;
    }

}
