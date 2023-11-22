package entities;
import java.util.UUID;
import java.util.HashMap;


public class Prompt {
    private final UUID promptId;
    public final String promptQuestion;

    private boolean isActive;

    private HashMap<UUID, UUID> responses;
    public Prompt(String promptQuestion) {
        this.promptQuestion = promptQuestion;
        this.promptId = UUID.randomUUID();
        this.isActive = false;
        this.responses = new HashMap<>();
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
    public HashMap<UUID, UUID> viewPromptResponse(){
        return responses;
    }
    public void setResponses(UUID userID, UUID responseId){
        if (!responses.containsKey(userID)) {
            responses.put(userID, responseId);
        }
    }
}