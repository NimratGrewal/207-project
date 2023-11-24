package use_case.toProfile;

import entities.Response;

import java.util.List;

public class ProfileOutputData {
    private final String username;
    private final List<Response> responseHistory;

    public ProfileOutputData(String username, List<Response> responseHistory) {
        this.username = username;
        this.responseHistory = responseHistory;
    }

    public String getUsername() {
        return username;
    }

    public List<Response> getResponseHistory() {
        return responseHistory;
    }

    public int getNumberOfResponses() {
        return responseHistory.size();
    }
}
