package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(UUID userId, String name, String password);
}
