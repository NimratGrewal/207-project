package use_case.toFeed;

import entities.Prompt;
import entities.Response;
import entities.Song;

import java.util.UUID;

public interface PromptDataAccessInterface {
    Prompt getPrompt(UUID promptId);

    Response getResponseById(UUID responseId);

    Song getSongById(UUID songId);
}
