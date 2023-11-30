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
    private final FeedDataAccessInterface feedDataAccessObject;
    private final FeedOutputBoundary presenter;

    public FeedInteractor(UserProfileDataAccessInterface userDataAccessObject,
                          FeedDataAccessInterface feedDataAccessObject,
                          FeedOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.feedDataAccessObject = feedDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(FeedInputData inputData) {
        UUID promptID = inputData.getDailyPromptID();

        String promptText = feedDataAccessObject.getCurrentPrompt(promptID).getPromptText();
        LocalDate promptDate = feedDataAccessObject.getCurrentPrompt(promptID).getPromptDate();
        Map<UUID, UUID> promptUserAndResponses = feedDataAccessObject.getPrompt(promptID).getPromptResponse();

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

            Song song = response.getSong();

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


