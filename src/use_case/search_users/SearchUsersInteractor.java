package use_case.search_users;


import data_access.DataAccessObjectFacade;
import entities.Prompt;
import entities.Response;
import entities.Song;
import entities.User;
import interface_adapter.search_users.SearchUsersPresenter;
import use_case.toFeed.FeedDataAccessInterface;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SearchUsersInteractor implements SearchUsersInputBoundary {
    private final SearchUsersOutputBoundary searchUsersPresenter;

    private final SearchUsersDataAccessInterface dataAccessObject;
    public SearchUsersInteractor(SearchUsersDataAccessInterface dataAccessObject, SearchUsersOutputBoundary searchUsersPresenter) {
        this.searchUsersPresenter = searchUsersPresenter;
        this.dataAccessObject = dataAccessObject;

    }

    @Override
    public void execute(SearchUsersInputData searchUsersInputData) {
        String username = searchUsersInputData.getUsername();

        if (dataAccessObject.usernameExists(username)){
            User user = dataAccessObject.getUsername(username);
            int numberOfResponses = user.getNumberOfResponses();
            Map<UUID, Response> userResponses = user.getHistory();
            Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();

            for (Map.Entry<UUID, Response> entry : userResponses.entrySet()) {
                UUID promptId = entry.getKey();
                Prompt prompt = dataAccessObject.getPromptById(promptId);
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
            SearchUsersOutputData searchUsersOutputData = new SearchUsersOutputData(user.getUsername(), numberOfResponses, responseInfoMap);
            searchUsersPresenter.prepareUserView(searchUsersOutputData);

        }
        searchUsersPresenter.prepareFailView("User not found!");
    }

    @Override
    public void profileToSearch() {
        searchUsersPresenter.prepareSearchView();
    }


}
