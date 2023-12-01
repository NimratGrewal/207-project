package interface_adapter.search_users;


import java.util.Map;
import java.util.UUID;

public class SearchUsersState {
    private String username;
    private int numberOfResponses;
    private Map<UUID, Map<String, Object>> responseInfoMap;

    public SearchUsersState(SearchUsersState copy) {
        username = copy.username;
        numberOfResponses = copy.numberOfResponses;
        responseInfoMap = copy.responseInfoMap;
    }
    public SearchUsersState(){
    }

    public String getUsername(){
        return username;
    }
    public int getNumberOfResponses() {
        return numberOfResponses;
    }
    public Map<UUID, Map<String, Object>> getResponseInfoMap() {
        return responseInfoMap;
    }


}
