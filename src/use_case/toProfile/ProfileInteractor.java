package use_case.toProfile;

import entities.Prompt;
import entities.Response;
import entities.Song;
import entities.User;
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

    public ProfileInteractor(UserProfileDataAccessInterface userDataAccessObject,
                             PromptDataAccessInterface promptDataAccessObject, ProfileOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.promptDataAccessObject = promptDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(ProfileInputData inputData) {
        UUID userId = inputData.getUserID();
        User user = userDataAccessObject.getUser(userId);

        Prompt prompt = promptDataAccessObject.getPrompt(inputData.getPromptID());

        LocalDate promptDate = prompt.getPromptDate();
        String promptText = prompt.getPromptText();

        String username = user.getUsername();
        int numberOfResponses = user.getNumberOfResponses();
        Map<UUID, Response> userResponses = user.getHistory();

        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
        for (Map.Entry<UUID, Response> entry : userResponses.entrySet()) {
            UUID promptId = entry.getKey();
            Response response = entry.getValue();

            Song song = response.getSong();
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

