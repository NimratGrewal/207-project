package use_case.toFeed;

import entities.Prompt;
import entities.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FeedInteractor implements FeedInputBoundary {
    private final PromptDataAccessObject promptDataAccessObject;
    private final FeedOutputBoundary presenter;

    public FeedInteractor(PromptDataAccessObject promptDataAccessObject, FeedOutputBoundary presenter) {
        this.promptDataAccessObject = promptDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(FeedInputData inputData) {
        UUID promptID = inputData.getPromptID();
        Prompt prompt = promptDataAccessObject.getPromptById(promptID);

        LocalDate promptDate = prompt.getPromptDate();
        List<Response> promptResponses = promptDataAccessObject.getPromptResponses(promptID);

        FeedOutputData outputData = new FeedOutputData(promptDate, prompt, promptResponses);

        presenter.present(outputData);
    }
}


