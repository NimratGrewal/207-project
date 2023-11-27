package use_case.signup;

import entities.User;
import entities.UserFactory;

import java.time.LocalDateTime;
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
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {

            LocalDateTime now = LocalDateTime.now();
            UUID uuid = UUID.randomUUID();
            User user = userFactory.create(uuid, signupInputData.getUsername(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}