package use_case.toFeed;

import entities.Prompt;

import java.util.UUID;

public interface PromptDataAccessInterface {
    Prompt getPrompt(UUID promptId);
}
