package entities;

import java.util.UUID;

public class CommonUserFactory implements UserFactory{

    @Override
    public User create(UUID userId, String name, String password) {
        return new CommonUser(userId, name, password);
    }
}
