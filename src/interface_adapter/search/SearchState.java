package interface_adapter.search;

public class SearchState {
    private String promptText = "";
    private String searchBarText = "";

    public SearchState() {};

    public SearchState(SearchState copy) {
        this.searchBarText = copy.getSearchBarText();
        this.promptText = copy.getPromptText();
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getSearchBarText() {
        return searchBarText;
    }

    public void setSearchBarText(String searchBarText) {
        this.searchBarText = searchBarText;
    }
}
