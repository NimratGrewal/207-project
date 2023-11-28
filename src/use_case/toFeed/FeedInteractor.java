package use_case.toFeed;

import entities.Response;
import entities.Song;
import entities.SpotifyAPICaller;
import entities.User;
import use_case.toProfile.UserProfileDataAccessInterface;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class FeedInteractor implements FeedInputBoundary {
    private final UserProfileDataAccessInterface userDataAccessObject;
    private final PromptDataAccessInterface promptDataAccessObject;
    private final FeedOutputBoundary presenter;
    private final SpotifyAPICaller spotifyAPICaller;

    public FeedInteractor(UserProfileDataAccessInterface userDataAccessObject,
                          PromptDataAccessInterface promptDataAccessObject,
                          FeedOutputBoundary presenter,
                          SpotifyAPICaller spotifyAPICaller) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
        this.presenter = presenter;
        this.spotifyAPICaller = spotifyAPICaller;
    }

    public void execute(FeedInputData inputData) {
        UUID promptID = inputData.getDailyPromptID();

        String promptText = promptDataAccessObject.getCurrentPrompt(promptID).getPromptText();
        LocalDate promptDate = promptDataAccessObject.getCurrentPrompt(promptID).getPromptDate();
        Map<UUID, UUID> promptUserAndResponses = promptDataAccessObject.getPrompt(promptID).getPromptResponse();

        List<UUID> promptResponses = new ArrayList<>();

        for (Map.Entry<UUID, UUID> entry : promptUserAndResponses.entrySet()) {
            UUID responseId = entry.getValue();
            promptResponses.add(responseId);
        }

        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
        for (UUID responseId : promptResponses) {
            Response response = userDataAccessObject.getResponseById(responseId);
            UUID userId = response.getUserId();

            User user = userDataAccessObject.getUser(userId);
            String username = user.getUsername();

            String songId = response.getSongId();
            Song song = spotifyAPICaller.getTrack(songId);

            ImageIcon albumArt = song.getAlbumArt(100);

            Map<String, Object> responseInfo = new HashMap<>();
            responseInfo.put("Response ID", responseId);
            responseInfo.put("Username", username);
            responseInfo.put("Song Name", song.getName());
            responseInfo.put("Song Artists", song.getArtists());
            responseInfo.put("Song Album", song.getAlbum());
            responseInfo.put("Album Art", albumArt);

            responseInfoMap.put(userId, responseInfo);
        }

        FeedOutputData outputData = new FeedOutputData(promptDate, promptText, responseInfoMap);

        presenter.present(outputData);
    }
}


