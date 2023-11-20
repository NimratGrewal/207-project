package use_case.signup;

public interface SignupUserDataInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
