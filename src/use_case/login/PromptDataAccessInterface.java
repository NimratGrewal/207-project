package use_case.login;

import entities.Prompt;

import java.util.UUID;

public interface PromptDataAccessInterface {
    Prompt getCurrentPrompt();

    boolean answeredCurrentPrompt(UUID promptID);
}
