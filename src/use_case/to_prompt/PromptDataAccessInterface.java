package use_case.to_prompt;

import entities.Prompt;
import entities.Response;
import entities.User;

public interface PromptDataAccessInterface {
    User getLoggedInUser();
    Prompt getActivePrompt();

    Response getLoggedInUserResponse();
}
