package use_case.toProfile;

import entities.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class ProfileInteractor implements ProfileInputBoundary {
    private final UserProfileDataAccessInterface userDataAccessObject;
    private final ProfileOutputBoundary presenter;

    public ProfileInteractor(UserProfileDataAccessInterface userDataAccessObject,
                             ProfileOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        User loggedInUser = userDataAccessObject.getLoggedInUser();

        if (loggedInUser != null) {
            String username = loggedInUser.getUsername();
            int numberOfResponses = loggedInUser.getNumberOfResponses();
            Map<UUID, Response> userResponses = loggedInUser.getHistory();

            Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
            for (Map.Entry<UUID, Response> entry : userResponses.entrySet()) {
                UUID promptId = entry.getKey();
                Prompt prompt = userDataAccessObject.getPromptById(promptId);
                String promptText = prompt.getPromptText();
                LocalDate promptDate = prompt.getPromptDate();

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
        } else {
            System.out.println("Error: getLoggedInUser() returned null");
        }
    }
}

