package interface_adapter.feed;

import entities.Prompt;
import entities.Response;

import java.time.LocalDate;
import java.util.List;

public class FeedState {
    private final LocalDate promptDate;
    private final Prompt promptOfTheDay;
    private final List<Response> promptResponses;

    public FeedState(LocalDate promptDate, Prompt promptOfTheDay, List<Response> promptResponses) {
        this.promptDate = promptDate;
        this.promptOfTheDay = promptOfTheDay;
        this.promptResponses = promptResponses;
    }

    public LocalDate getPromptDate() {
        return promptDate;
    }

    public Prompt getPromptOfTheDay() {
        return promptOfTheDay;
    }

    public List<Response> getPromptResponses() {
        return promptResponses;
    }
}
