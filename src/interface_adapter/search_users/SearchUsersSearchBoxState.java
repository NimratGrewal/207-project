package interface_adapter.search_users;


public class SearchUsersSearchBoxState {
    private String username = "";
    private String usernameError = null;
    public final String RETURN_LABEL = "RETURN";

    public SearchUsersSearchBoxState(SearchUsersSearchBoxState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }
    public SearchUsersSearchBoxState() {}
    public String getUsername(){
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
}
