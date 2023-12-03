package interface_adapter.prompt;

import use_case.to_prompt.PromptInputBoundary;

public class PromptController {
    private final PromptInputBoundary promptInteractor;

    public PromptController(PromptInputBoundary promptInteractor) {
        this.promptInteractor = promptInteractor;
    }

    public void execute() {
        promptInteractor.execute();
    }
}
