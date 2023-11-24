package interface_adapter.profile;

import java.util.List;

public class ProfileViewModel {
    private final String username;
    private final List<String> responseHistory; // Replace with actual response data type

    public ProfileViewModel(String username, List<String> responseHistory) {
        this.username = username;
        this.responseHistory = responseHistory;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getResponseHistory() {
        return responseHistory;
    }
}
