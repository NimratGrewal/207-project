package use_case.toProfile;

import entities.*;
import use_case.toFeed.PromptDataAccessInterface;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class ProfileInteractor implements ProfileInputBoundary {
    private final UserProfileDataAccessInterface userDataAccessObject;
    private final PromptDataAccessInterface promptDataAccessObject;
    private final ProfileOutputBoundary presenter;
    private final SpotifyAPICaller spotifyAPICaller;

    public ProfileInteractor(UserProfileDataAccessInterface userDataAccessObject,
                             PromptDataAccessInterface promptDataAccessObject,
                             ProfileOutputBoundary presenter,
                             SpotifyAPICaller spotifyAPICaller) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
        this.presenter = presenter;
        this.spotifyAPICaller = spotifyAPICaller;
    }

    @Override
    public void execute(ProfileInputData inputData) {
        UUID userId = inputData.getLoggedInUserID();
        User user = userDataAccessObject.getLoggedInUser(userId);

        String username = user.getUsername();
        int numberOfResponses = user.getNumberOfResponses();
        Map<UUID, Response> userResponses = user.getHistory();

        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
        for (Map.Entry<UUID, Response> entry : userResponses.entrySet()) {
            UUID promptId = entry.getKey();
            Prompt prompt = promptDataAccessObject.getPrompt(promptId);
            String promptText = prompt.getPromptText();
            LocalDate promptDate = prompt.getPromptDate();

            Response response = entry.getValue();

            String songId = response.getSongId();
            Song song = spotifyAPICaller.getTrack(songId);

            ImageIcon albumArt = song.getAlbumArt(100);

            Map<String, Object> responseInfo = new HashMap<>();
            responseInfo.put("Prompt Date", promptDate);
            responseInfo.put("Prompt Text", promptText);
            responseInfo.put("Song Name", song.getName());
            responseInfo.put("Song Artists", song.getArtists());
            responseInfo.put("Song Album", song.getAlbum());
            responseInfo.put("Album Art", albumArt);

            responseInfoMap.put(promptId, responseInfo);
        }

        ProfileOutputData outputData = new ProfileOutputData(username, numberOfResponses, responseInfoMap);

        presenter.present(outputData);
    }
}

