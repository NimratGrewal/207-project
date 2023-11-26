package use_case.signup;

import entities.User;

public interface SignupUserDataInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
