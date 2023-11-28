package interface_adapter.set_response;

import use_case.set_response.SetResponseInputData;
import use_case.set_response.SetResponseInteractor;

import java.util.UUID;

public class SetResponseController {
    private final SetResponseInteractor setResponseInteractor;

    public SetResponseController(SetResponseInteractor setResponseInteractor) {
        this.setResponseInteractor = setResponseInteractor;
    }

    public void execute(String songId) {
        SetResponseInputData setResponseInputData = new SetResponseInputData(songId);
        setResponseInteractor.execute(setResponseInputData);
    }
}
