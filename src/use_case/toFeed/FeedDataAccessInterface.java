package use_case.toFeed;

import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface FeedDataAccessInterface {
    Prompt getPromptById(UUID promptId);

    Prompt getCurrentPromptById(UUID promptId);

    User getUser(UUID userId);
}
