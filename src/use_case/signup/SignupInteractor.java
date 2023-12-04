package use_case.signup;

import entities.User;
import entities.UserFactory;

import java.util.UUID;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();

        // username and password minimum 6 characters
        if (username.length() < 6 || password.length() < 6) {
            userPresenter.prepareFailView("Username and password must be at least 6 characters long.");
            return;
        }

        // empty username or password error
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            userPresenter.prepareFailView("Username and password cannot be empty.");
            return;
        }

        // error for if there is a space in username or password
        if (username.contains(" ") || password.contains(" ")) {
            userPresenter.prepareFailView("Username and password cannot contain spaces.");
            return;
        }


        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {

            User user = userFactory.create(UUID.randomUUID(), signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}