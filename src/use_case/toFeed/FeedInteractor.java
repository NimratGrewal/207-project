package use_case.toFeed;

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

        // necessary info from prompt dao... need these methods!!!
        String promptText = promptDataAccessObject.getPromptByID(promptID).getPromptText();
        LocalDate promptDate = promptDataAccessObject.getPromptByID(promptID).getPromptDate();
        List<UUID> promptResponses = promptDataAccessObject.getPromptResponses(promptID);

        FeedOutputData outputData = new FeedOutputData(promptDate, promptText, promptResponses);

        presenter.present(outputData);
    }
}


