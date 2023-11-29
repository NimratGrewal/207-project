package interface_adapter.profile;

import java.util.Map;
import java.util.UUID;

public class ProfileState {
    private String username;
    private int numberOfResponses;
    private Map<UUID, Map<String, Object>> responseInfoMap;

    public ProfileState(ProfileState copy) {
        username = copy.username;
        numberOfResponses = copy.numberOfResponses;
        responseInfoMap = copy.responseInfoMap;
    }

    public ProfileState() {}

    public String getUsername() {
        return username;
    }
    public int getNumberOfResponses() {
        return numberOfResponses;
    }
    public Map<UUID, Map<String, Object>> getResponseInfoMap() {
        return responseInfoMap;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setNumberOfResponses(int numberOfResponses) {
        this.numberOfResponses = numberOfResponses;
    }
    public void setResponseInfoMap(Map<UUID, Map<String, Object>> responseInfoMap) {
        this.responseInfoMap = responseInfoMap;
    }
}
