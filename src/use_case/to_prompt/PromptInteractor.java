package use_case.to_prompt;

import entities.Response;
import entities.Song;
import entities.User;

public class PromptInteractor implements PromptInputBoundary {
    private final PromptDataAccessInterface dataAccessObject;
    private final PromptOutputBoundary promptPresenter;

    public PromptInteractor(PromptDataAccessInterface dataAccessObject, PromptOutputBoundary promptPresenter) {
        this.dataAccessObject = dataAccessObject;
        this.promptPresenter = promptPresenter;
    }

    @Override
    public void execute() {
        User loggedInUser = dataAccessObject.getLoggedInUser();
        PromptOutputData promptOutputData;
        if (loggedInUser.getHistory().containsKey(dataAccessObject.getActivePrompt().getPromptId())) {
            Song response = dataAccessObject.getLoggedInUserResponse().getSong();
            promptOutputData = new PromptOutputData(response.getName(),
                    String.join(", ", response.getArtists()),
                    response.getAlbum(),
                    dataAccessObject.getActivePrompt().getPromptText(),
                    response.getAlbumArt(150));

            promptPresenter.prepareViewResponseView(promptOutputData);
        } else {
            promptOutputData = new PromptOutputData(dataAccessObject.getActivePrompt().getPromptText());
            promptPresenter.prepareSearchView(promptOutputData.getPromptText());
        }
    }
}
