package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import views.StartView;

public class StartUseCaseFactory {
    public static StartView create(
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            ViewManagerModel viewManagerModel
    ) {
        return new StartView(loginViewModel, signupViewModel, viewManagerModel);
    }
}
