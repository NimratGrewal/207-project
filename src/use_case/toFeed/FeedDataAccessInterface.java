package use_case.toFeed;

import entities.Prompt;
import entities.Response;
import entities.User;

import java.util.UUID;

public interface FeedDataAccessInterface {
    Prompt getPromptById(UUID promptId);

    Prompt getCurrentPrompt();

    User getUser(UUID userId);

    Response getResponseById(UUID responseId);
}
