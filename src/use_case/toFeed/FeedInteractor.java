package use_case.toFeed;

import data_access.FileUserDataAccessObject;
import data_access.PromptDataAccessObject;
import entities.Response;
import entities.Song;
import entities.SpotifyAPICaller;
import entities.User;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedInteractor implements FeedInputBoundary {
    private final FileUserDataAccessObject userDataAccessObject;
    private final PromptDataAccessObject promptDataAccessObject;
    private final FeedOutputBoundary presenter;

    public FeedInteractor(FileUserDataAccessObject userDataAccessObject,
                          PromptDataAccessObject promptDataAccessObject, FeedOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(FeedInputData inputData) {
        UUID promptID = inputData.getPromptID();

        String promptText = promptDataAccessObject.getPromptByID(promptID).getPromptText();
        LocalDate promptDate = promptDataAccessObject.getPromptByID(promptID).getPromptDate();
        List<UUID> promptResponses = promptDataAccessObject.getResponses(promptID);

        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
        for (UUID responseId : promptResponses) {
            //TODO: create getResponseById method somewhere
            Response response = promptDataAccessObject.getResponseById(responseId);
            UUID userId = response.getUserId();

            User user = userDataAccessObject.getUser(userId);
            String username = user.getUsername();

            UUID songId = response.getSongId();
            //TODO: create getSongById method somewhere
            Song song = promptDataAccessObject.getSongById(songId);

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


