package use_case.login;

public class LoginOutputData {
    private final String username;
    private boolean answeredPrompt;

    public LoginOutputData(String username, boolean answeredPrompt) {
        this.username = username;
        this.answeredPrompt = answeredPrompt;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAnsweredPrompt(){
        return answeredPrompt;
    }

}
