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

    public void setUsername(String username){
        this.username = username;
    }

    public void setNumberOfResponses(Integer numberOfResponses){
        this.numberOfResponses = numberOfResponses;
    }

    public void setResponseInfoMap(Map<UUID, Map<String, Object>> responseInfoMap){
        this.responseInfoMap = responseInfoMap;
    }

}
