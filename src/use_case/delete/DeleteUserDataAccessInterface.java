package use_case.delete;

import java.util.UUID;

public interface DeleteUserDataAccessInterface {
    boolean responseExistsById(UUID responseId);

    void deleteResponse(UUID responseId);
}
