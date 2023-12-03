package use_case.to_prompt;

import entities.User;

public class PromptInteractor implements PromptInputBoundary {
    private final PromptDataAccessInterface dataAccessObject;
    private final PromptOutputBoundary promptPresenter;
    private final PromptOutputData promptOutputData;

    public PromptInteractor(PromptDataAccessInterface dataAccessObject, PromptOutputBoundary promptPresenter, PromptOutputData promptOutputData) {
        this.dataAccessObject = dataAccessObject;
        this.promptPresenter = promptPresenter;
        this.promptOutputData = promptOutputData;
    }

    @Override
    public void execute() {
        User loggedInUser = dataAccessObject.getLoggedInUser();
        if (loggedInUser.getHistory().containsKey(dataAccessObject.getActivePrompt().getPromptId())) {
            promptPresenter.prepareViewResponseView(promptOutputData);
        } else {
            promptPresenter.prepareSearchView(promptOutputData.getPromptText());
        }
    }
}
