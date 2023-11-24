package entities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;


public class Prompt {
    private final UUID promptid;
    private final String promptQuestion;
    private final LocalDateTime creationTime;

    private String date;
    private boolean isActive;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private HashMap<UUID, UUID> responses;

    public Prompt(String promptQuestion, String date) throws ParseException {
        this.promptQuestion = promptQuestion;
        this.promptid = UUID.randomUUID();
        this.responses = new HashMap<>();
        this.date = date;
        this.creationTime = LocalDateTime.now();
    }

    public UUID getPromptId() {
        return promptid;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDate() {
        return date;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPromptText() {
        return promptQuestion;
    }

    public HashMap<UUID, UUID> viewPromptResponse() {
        return responses;
    }

    public void setResponses(UUID userID, UUID responseId) {
        if (!responses.containsKey(userID)) {
            responses.put(userID, responseId);
        }
    }

    public List<UUID> getAllResponses() {
        return (List<UUID>) responses.values();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}