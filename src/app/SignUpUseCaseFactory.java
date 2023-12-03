package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataInterface;
import views.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignUpUseCaseFactory {

    // sign up use case factory
    private SignUpUseCaseFactory() {}

//    public static SignupView create(
//            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
//            SignupViewModel signupViewModel, SignupUserDataInterface userDataAccessObject) {
//
//        try {
//
//            SignupController signupController = createSignUpUseCase(viewManagerModel, signupViewModel,
//                    loginViewModel, userDataAccessObject);
//            return new SignupView(signupController, signupViewModel);
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//
//        return null;
//    }

    private static SignupController createSignUpUseCase(

            ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
            SignupUserDataInterface signupUserDataInterface) throws IOException {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel,
                loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                signupUserDataInterface, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

}
