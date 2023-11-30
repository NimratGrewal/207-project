package use_case.set_response;

import entities.Response;
import entities.Song;
import entities.SpotifyAPICaller;

public class SetResponseInteractor implements SetResponseInputBoundary {
    private final SetResponseDataAccessInterface dataAccessObject;
    private final SetResponseOutputBoundary setResponsePresenter;

    private final SpotifyAPICaller caller;

    public SetResponseInteractor(SetResponseDataAccessInterface dataAccessObject,
                                 SetResponseOutputBoundary setResponsePresenter, SpotifyAPICaller caller) {
        this.dataAccessObject = dataAccessObject;
        this.setResponsePresenter = setResponsePresenter;
        this.caller = caller;
    }

    @Override
    public void execute(SetResponseInputData setResponseInputData) {
        Song song = caller.getTrack(setResponseInputData.getSongId());

        Response response = new Response(
                dataAccessObject.getActivePromptId(),
                dataAccessObject.getLoggedInUser().getUserId(),
                song);
        dataAccessObject.setResponse(response);

        SetResponseOutputData setResponseOutputData = new SetResponseOutputData(
                song.getName(),
                song.getAlbum(),
                String.join(", ", song.getArtists()),
                dataAccessObject.getActivePromptText(),
                song.getAlbumArt(100));
        setResponsePresenter.prepareSuccessView(setResponseOutputData);
    }
}
