package use_case.set_response;

import entities.Response;
import entities.Song;
import entities.SpotifyAPICaller;

public class SetResponseInteractor implements SetResponseInputBoundary {
    private final SetResponseDataAccessInterface userDataAccessObject;
    private final SetResponseDataAccessInterface promptDataAccessObject;
    private final SetResponseOutputBoundary setResponsePresenter;

    private final SpotifyAPICaller caller;

    public SetResponseInteractor(SetResponseDataAccessInterface userDataAccessObject,
                                 SetResponseDataAccessInterface promptDataAccessObject,
                                 SetResponseOutputBoundary setResponsePresenter, SpotifyAPICaller caller) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
        this.setResponsePresenter = setResponsePresenter;
        this.caller = caller;
    }

    @Override
    public void execute(SetResponseInputData setResponseInputData) {
        Song song = caller.getTrack(setResponseInputData.getSongId());

        Response response = new Response(
                userDataAccessObject.getLoggedInUserId(),
                promptDataAccessObject.getActivePromptId(),
                song);
        userDataAccessObject.setResponse(response);
        promptDataAccessObject.setResponse(response);

        SetResponseOutputData setResponseOutputData = new SetResponseOutputData(
                song.getName(),
                song.getAlbum(),
                String.join(", ", song.getArtists()),
                promptDataAccessObject.getActivePromptText()
        );
        setResponsePresenter.prepareSuccessView(setResponseOutputData);
    }
}
