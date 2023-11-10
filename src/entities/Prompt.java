package entities;
import java.util.UUID;
import java.util.HashMap;


public class Prompt {
    private final UUID promptid;
    public final String promptQuestion;

    private boolean isActive;

    private HashMap <UUID, Response> responses;
    public Prompt(String promptQuestion) {
        this.promptQuestion = promptQuestion;
        this.promptid = UUID.randomUUID();
        this.isActive = false;
        this.responses = new HashMap<UUID, Response>();
    }

    public UUID getPromptId() {
        return promptid;
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
    public HashMap <UUID, Response>  viewPromptResponse(){
        return responses;
    }
    public void setResponses(UUID uuid, Response response){
        if (!responses.containsKey(uuid)) {
            responses.put(uuid, response);
        }
    }
}