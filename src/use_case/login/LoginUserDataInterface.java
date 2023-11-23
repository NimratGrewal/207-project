package use_case.login;
import entities.User;
public interface LoginUserDataInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
