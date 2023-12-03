package use_case.to_prompt;

public interface PromptOutputBoundary {
    void prepareSearchView(String promptText);
    void prepareViewResponseView(PromptOutputData promptOutputData);
}
