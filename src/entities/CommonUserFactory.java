package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommonUserFactory implements UserFactory{

    @Override
    public User create(UUID userId, String name, String password, LocalDateTime creationTime) {
        return new CommonUser(userId, name, password, creationTime);
    }
}
