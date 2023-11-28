package entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;


public class Prompt {
    private final UUID promptId;
    public final String promptQuestion;

    private boolean isActive;
    private LocalDate promptDate;

    private List<UUID> responses;
    public Prompt(String promptQuestion, LocalDate promptDate) {
        this.promptQuestion = promptQuestion;
        this.promptId = UUID.randomUUID();
        this.isActive = false;
        this.responses = new ArrayList<>();
        this.promptDate = promptDate;
    }

    public UUID getPromptId() {
        return promptId;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean active){
        isActive = active;
    }

    public String getPromptText(){
        return promptQuestion;
    }
    public LocalDate getPromptDate() {
        return promptDate;
    }
    public List<UUID> getPromptResponse(){
        return responses;
    }
    public void setResponses(UUID userID, UUID responseId){
        if (!responses.containsKey(userID)) {
            responses.put(userID, responseId);
        }
    }
}