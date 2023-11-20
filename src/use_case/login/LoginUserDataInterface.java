package use_case.login;

public interface LoginUserDataInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
