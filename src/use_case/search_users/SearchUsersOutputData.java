package use_case.search_users;

import java.util.Map;
import java.util.UUID;

public class SearchUsersOutputData {
    private final String username;
    private final int numberOfResponses;
    private final Map<UUID, Map<String, Object>> responseInfoMap;

    public SearchUsersOutputData(String username, int numberOfResponses, Map<UUID, Map<String, Object>> responseInfoMap) {
        this.username = username;
        this.numberOfResponses = numberOfResponses;
        this.responseInfoMap = responseInfoMap;
    }
    public String getUsername() {
        return username;
    }
    public int getNumberOfResponses() {
        return numberOfResponses;
    }
    public Map<UUID, Map<String, Object>> getResponseInfoMap() {
        return responseInfoMap;
    }
}
