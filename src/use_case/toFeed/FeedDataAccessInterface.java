package use_case.toFeed;

import entities.Prompt;
import entities.User;

import java.util.UUID;

public interface FeedDataAccessInterface {
    Prompt getPrompt(UUID promptId);

    Prompt getCurrentPrompt(UUID promptId);

    User getUser(UUID userId);
}
