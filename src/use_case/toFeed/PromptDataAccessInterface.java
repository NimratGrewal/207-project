package use_case.toFeed;

import entities.Prompt;
import entities.Response;
import entities.Song;
import entities.User;

import java.util.UUID;

public interface PromptDataAccessInterface {
    Prompt getPrompt(UUID promptId);

    Prompt getCurrentPrompt(UUID promptId);

    User getUser(UUID userId);
}
