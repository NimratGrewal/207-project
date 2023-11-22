package use_case.delete;

import java.util.UUID;

public interface DeleteResponseDataAccessInterface {

    boolean existsById(UUID responseId);

    void deleteResponse(UUID responseId);
}
