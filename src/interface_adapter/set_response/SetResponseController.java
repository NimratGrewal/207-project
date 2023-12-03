package interface_adapter.set_response;

import use_case.set_response.SetResponseInputBoundary;
import use_case.set_response.SetResponseInputData;

import java.util.UUID;

public class SetResponseController {
    private final SetResponseInputBoundary setResponseInteractor;

    public SetResponseController(SetResponseInputBoundary setResponseInteractor) {
        this.setResponseInteractor = setResponseInteractor;
    }

    public void execute(String songId) {
        SetResponseInputData setResponseInputData = new SetResponseInputData(songId);
        setResponseInteractor.execute(setResponseInputData);
    }
}
