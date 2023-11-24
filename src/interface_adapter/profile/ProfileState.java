package interface_adapter.profile;

import entities.Response;

import java.util.List;

public class ProfileState {
    private final String username;
    private final List<Response> responseHistory;
    private final int numberOfResponses;

    public ProfileState(String username, List<Response> responseHistory, int numberOfResponses) {
        this.username = username;
        this.responseHistory = responseHistory;
        this.numberOfResponses = numberOfResponses;
    }

    public String getUsername() {
        return username;
    }

    public List<Response> getResponseHistory() {
        return responseHistory;
    }

    public int getNumberOfResponses() {
        return numberOfResponses;
    }
}
