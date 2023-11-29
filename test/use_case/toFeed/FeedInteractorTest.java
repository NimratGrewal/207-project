package use_case.toFeed;

import static org.junit.jupiter.api.Assertions.*;

import data_access.DataAccessObjectFacade;
import data_access.FileUserDataAccessObject;
import data_access.PromptDataAccessObject;
import entities.*;
import mocks.MockDataAccessObjectFacade;
import mocks.MockFileUserDataAccessObject;
import mocks.MockPromptDataAccessObject;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

class FeedInteractorTest {

    //making users
    CommonUser user1 = new CommonUser("haya.tariqq", "password1");
    UUID user1Id = user1.getUserId();
    CommonUser user2 = new CommonUser("newr", "password2");
    UUID user2Id = user2.getUserId();

    //making daily prompt
    Prompt dailyPrompt = new Prompt("What is your go-to song to get ready to?", LocalDate.now());
    UUID dailyPromptId = dailyPrompt.getPromptId();

    //making songs
    ImageIcon albumArt = new ImageIcon("placeholderAlbum");

    List<String> artists1 = new ArrayList<String>() {{
        add("Mitski");
    }};
    Song song1 = new Song("10", "My Love Mine All Mine", artists1,
            "This Land is Inhospitable and So Are We", albumArt);

    List<String> artists2 = new ArrayList<String>() {{
        add("Baby Keem");
        add("Brent Faiyaz");
    }};
    Song song2 = new Song("20", "lost souls (with Brent Faiyaz)", artists2,
            "The Melodic Blue", albumArt);


    //making responses
    Response response1 = new Response(dailyPromptId, user1Id, song1);
    Response response2 = new Response(dailyPromptId, user2Id, song2);

    FeedInputData inputData = new FeedInputData(dailyPromptId);

    Map<String, Object> responseInfo1 = new HashMap<String, Object>() {{
        put("ResponseId", response1.getResponseId());
        put("Username", "haya.tariqq");
        put("Song Name", "My Love Mina All Mine");
        put("Song Artists", artists1);
        put("Song Album", "This Land is Inhospitable and So Are We");
        put("Album Art", albumArt);
    }};

    Map<String, Object> responseInfo2 = new HashMap<String, Object>() {{
        put("ResponseId", response2.getResponseId());
        put("Username", "newr");
        put("Song Name", "lost souls (with Brent Faiyaz)");
        put("Song Artists", artists2);
        put("Song Album", "The Melodic Blue");
        put("Album Art", albumArt);
    }};

    Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>() {{
        put(user1Id, responseInfo1);
        put(user2Id, responseInfo2);
    }};

    FeedOutputData outputData = new FeedOutputData(LocalDate.now(),
            "What is your go-to song to get ready to?", responseInfoMap);

    @Test
    void executeTest() {

    }
 }