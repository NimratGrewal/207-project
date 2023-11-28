package use_case.delete;

import java.util.UUID;

public interface DeleteResponseDataAccessInterface {

    boolean responseexistsById(UUID responseId);

    void deleteResponse(UUID responseId);
}
