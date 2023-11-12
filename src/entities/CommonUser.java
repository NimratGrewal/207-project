package entities;

import java.util.HashMap;

public class CommonUser implements User {

    private final String username;

    private final String password;
    private final HashMap<Prompt, Response> history;


    /**
     * Requires: password is valid.
     *
     * @param username
     * @param password
     * @param history
     */

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.history = new HashMap<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public HashMap<Prompt, Response> getHistory() {
        return history;
    }

    @Override
    public Response getResponse(Prompt prompt) {
        return history.get(prompt);
    }

    @Override
    public void setResponse(Prompt prompt, Response response) {
        // if this prompt is not yet in history:
        if (!history.containsKey(prompt)) {
            this.history.put(prompt, response);
        } else {
            // trying to set a response to a prompt that has an answer;
            // raise some type of error here;
        }
    }

    @Override
    public void deleteResponse(Prompt prompt) {
        history.remove(prompt);
    }

    @Override
    public void changeResponse(Prompt prompt, Response response) {
        if (history.containsKey(prompt)) {
            history.replace(prompt, response);
        }
    } // testing out branching pull request approval process
}
