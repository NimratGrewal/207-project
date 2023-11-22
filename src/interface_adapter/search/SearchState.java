package interface_adapter.search;

public class SearchState {
    private String promptText = "";

    public SearchState() {};

    public SearchState(SearchState copy) {
        this.promptText = copy.getPromptText();
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }
}
