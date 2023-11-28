package use_case.toFeed;

import static org.junit.jupiter.api.Assertions.*;

import data_access.PromptDataAccessObject;
import entities.CommonUser;
import entities.Prompt;
import entities.Response;
import entities.Song;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class FeedInteractorTest {
    void successTest() throws IOException {
        CommonUser hypotheticalUser = new CommonUser("JohnDoe", "password123");
        List<String> artist = List.of("Mitski");
        Image placeholderImage = createPlaceholderImage();
        Song hypotheticalSong = new Song("1", "My Love Mine All Mine", artist,
                "The Land Is Inhospitable and So Are We", placeholderImage);
        UUID responseId = UUID.randomUUID();
        UUID promptId = UUID.randomUUID();
        Response hypotheticalResponse = new Response(responseId, promptId, hypotheticalUser, hypotheticalSong);
        String promptQuestion = "Hypothetical Prompt Question";
        LocalDate promptDate = LocalDate.now();
        Prompt hypotheticalPrompt = new Prompt(promptQuestion, promptDate);

        FeedInputData inputData = new FeedInputData(promptId);

        // MockPromptDataAccessObject includes the hypothetical response
        PromptDataAccessObject mockPromptDao = new PromptDataAccessObject("mock.csv");

        FeedOutputBoundary successPresenter = new FeedOutputBoundary() {
            @Override
            public void present(FeedOutputData outputData) {
                //assertions
            }
        };

        FeedInputBoundary interactor = new FeedInteractor(mockPromptDao, successPresenter);
        interactor.execute(inputData);
    }

    private Image createPlaceholderImage() {
        // Implement your logic to create a placeholder Image object
        // For example, you can use Toolkit.getDefaultToolkit().getImage("path/to/your/placeholder/image.jpg");
        return null; // Replace null with your actual implementation
    }
}