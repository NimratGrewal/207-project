package use_case.toProfile;

import entities.*;
import interface_adapter.feed.FeedController;
import interface_adapter.profile.ProfileController;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.toFeed.*;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileInteractorTest {
    @Test
    void testExecuteProfile() {
        Prompt dailyPrompt = new Prompt("Daily Prompt!", LocalDate.now(), UUID.randomUUID());
        UUID dailyPromptId = dailyPrompt.getPromptId();

        User user1 = new CommonUser("user2001", "password1");
        UUID user1Id = user1.getUserId();
        User user2 = new CommonUser("user2002", "password2");
        UUID user2Id = user2.getUserId();

        URL imageURL = FeedInteractorTest.class.getResource("/views/assets/placeholderAlbum.jpg");
        ImageIcon albumArt = new ImageIcon(imageURL);
        java.util.List<String> artists1 = new ArrayList<String>() {{
            add("Mitski");
        }};

        Song song1 = new Song("10", "My Love Mine All Mine", artists1,
                "This Land is Inhospitable and So Are We", albumArt.getImage());

        List<String> artists2 = new ArrayList<String>() {{
            add("Baby Keem");
            add("Brent Faiyaz");
        }};

        Song song2 = new Song("20", "lost souls (with Brent Faiyaz)", artists2,
                "The Melodic Blue", albumArt.getImage());

        Response response1 = new Response(dailyPromptId, user1Id, song1);
        Response response2 = new Response(dailyPromptId, user2Id, song2);

        user1.setResponse(dailyPromptId, response1);
        user2.setResponse(dailyPromptId, response2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Verify that the presenter is called with the expected output data
        Map<UUID, Map<String, Object>> responseInfoMap = new HashMap<>();
        Map<String, Object> responseInfo1 = new HashMap<>();
        responseInfo1.put("Response ID", response1.getResponseId());
        responseInfo1.put("Username", user1.getUsername());
        responseInfo1.put("Song Name", song1.getName());
        responseInfo1.put("Song Artists", song1.getArtists());
        responseInfo1.put("Song Album", song1.getAlbum());
        responseInfo1.put("Album Art", song1.getAlbumArt(100));
        responseInfoMap.put(user1.getUserId(), responseInfo1);

        ProfileOutputBoundary mockPresenter = mock(ProfileOutputBoundary.class);

        UserProfileDataAccessInterface userProfileDataAccess = mock(UserProfileDataAccessInterface.class);

        when(userProfileDataAccess.getLoggedInUser()).thenReturn(user1);
        when(userProfileDataAccess.getPromptById(dailyPromptId)).thenReturn(dailyPrompt);

        ProfileInteractor interactor = new ProfileInteractor(userProfileDataAccess, mockPresenter);
        ProfileController controller = new ProfileController(interactor);

        controller.execute();

        ProfileOutputData expectedOutput = new ProfileOutputData("user2001", 1, responseInfoMap);

        verify(userProfileDataAccess, times(1)).getLoggedInUser();
        verify(userProfileDataAccess, times(1)).getPromptById(dailyPromptId);

        ArgumentCaptor<ProfileOutputData> captor = ArgumentCaptor.forClass(ProfileOutputData.class);
        verify(mockPresenter).present(captor.capture());

        ProfileOutputData actualOutput = captor.getValue();

        assertEquals(expectedOutput.getUsername(), actualOutput.getUsername());
        assertEquals(expectedOutput.getNumberOfResponses(), actualOutput.getNumberOfResponses());

    }

}