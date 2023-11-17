package entities;

import java.util.HashMap;
import java.util.UUID;

public class CommonUser implements User {

    private final String username;

    private final UUID userId;

    private final String password;
    private final HashMap<UUID, Response> history;


    /**
     * Requires: password is valid.
     *
     * @param username
     * @param password
     */

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.userId = UUID.randomUUID();
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
    public UUID getUserId() { return userId; }

    @Override
    public HashMap<UUID, Response> getHistory() {
        return history;
    }

    @Override
    public Response getResponse(UUID promptId) {
        return history.get(promptId);
    }

    @Override
    public void setResponse(UUID promptId, Response response) {
        // if this prompt is not yet in history:
        if (!history.containsKey(promptId)) {
            this.history.put(promptId, response);
        } else {
            // trying to set a response to a prompt that has an answer;
            // raise some type of error here;
        }
    }

    @Override
    public void deleteResponse(UUID promptId) {
        history.remove(promptId);
    }

    @Override
    public void changeResponse(UUID promptId, Response response) {
        if (history.containsKey(promptId)) {
            history.replace(promptId, response);
        }
    }
}
