package use_case.toFeed;

import entities.Prompt;
import entities.Response;
import entities.Song;
import entities.User;
import use_case.toProfile.UserProfileDataAccessInterface;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;


public class FeedInteractor implements FeedInputBoundary {
    private final FeedDataAccessInterface feedDataAccessObject;
    private final FeedOutputBoundary presenter;

    public FeedInteractor(FeedDataAccessInterface feedDataAccessObject,
                          FeedOutputBoundary presenter) {
        this.feedDataAccessObject = feedDataAccessObject;
        this.presenter = presenter;
    }

    public void execute() {
        Prompt currentPrompt = feedDataAccessObject.getCurrentPrompt();
        UUID dailyPromptId = currentPrompt.getPromptId();

        String promptText = currentPrompt.getPromptText();
        LocalDate promptDate = currentPrompt.getPromptDate();

        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();

        // Iterate through all users
        for (User user : feedDataAccessObject.getAllUsers()) {
            UUID userId = user.getUserId();

            // Check if the user has a response for the given daily prompt
            if (user.hasResponseForDailyPrompt(dailyPromptId)) {
                Response response = user.getResponseForDailyPrompt(dailyPromptId);

                String username = user.getUsername();

                Song song = response.getSong();
                ImageIcon albumArt = song.getAlbumArt(100);

                Map<String, Object> responseInfo = new HashMap<>();
                responseInfo.put("Response ID", response.getResponseId());
                responseInfo.put("Username", username);
                responseInfo.put("Song Name", song.getName());
                responseInfo.put("Song Artists", song.getArtists());
                responseInfo.put("Song Album", song.getAlbum());
                responseInfo.put("Album Art", albumArt);

                responseInfoMap.put(userId, responseInfo);
            }
        }

        FeedOutputData outputData = new FeedOutputData(promptDate, promptText, responseInfoMap);
        presenter.present(outputData);
    }
}


