package use_case.toFeed;

import entities.*;
import interface_adapter.feed.FeedController;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FeedInteractorTest {
 @Test
 void testExecuteFeed() {
  // Mock data
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

  Map<String, Object> responseInfo2 = new HashMap<>();
  responseInfo2.put("Response ID", response2.getResponseId());
  responseInfo2.put("Username", user2.getUsername());
  responseInfo2.put("Song Name", song2.getName());
  responseInfo2.put("Song Artists", song2.getArtists());
  responseInfo2.put("Song Album", song2.getAlbum());
  responseInfo2.put("Album Art", song2.getAlbumArt(100));
  responseInfoMap.put(user2.getUserId(), responseInfo2);

  FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

  FeedDataAccessInterface feedDataAccess = mock(FeedDataAccessInterface.class);

  when(feedDataAccess.getAllUsers()).thenReturn(users);
  when(feedDataAccess.getCurrentPrompt()).thenReturn(dailyPrompt);

  FeedInteractor interactor = new FeedInteractor(feedDataAccess, mockPresenter);
  FeedController controller = new FeedController(interactor);

  // Call the method to be tested
  controller.execute();

  FeedOutputData expectedOutput = new FeedOutputData(LocalDate.now(), "Daily Prompt!", responseInfoMap);

  verify(feedDataAccess, times(1)).getCurrentPrompt();
  verify(feedDataAccess, times(1)).getAllUsers();

  ArgumentCaptor<FeedOutputData> captor = ArgumentCaptor.forClass(FeedOutputData.class);
  verify(mockPresenter).present(captor.capture());

  FeedOutputData actualOutput = captor.getValue();

  assertEquals(expectedOutput.getPromptDate(), actualOutput.getPromptDate());
  assertEquals(expectedOutput.getPromptText(), actualOutput.getPromptText());
 }
}