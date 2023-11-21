package interface_adapter.profile_view;

import java.util.List;

public class ProfileState {
    private String username;
    private int numResponses;
    //where the user's response info will be (responseId(use UUID.toString), promptText, songName, artistNames, album name...)
    private List<String[]> responses;
}
