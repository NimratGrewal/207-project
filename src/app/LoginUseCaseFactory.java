package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_response.ViewResponseViewModel;
import use_case.login.*;
import views.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            LoginViewModel loginViewModel,
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ViewResponseViewModel viewResponseViewModel,
            LoginUserDataInterface loginUserDataInterface) {

        try {
            LoginController loginController = createLoginUseCase(
                    loginViewModel,
                    viewManagerModel,
                    searchViewModel,
                    viewResponseViewModel,
                    loginUserDataInterface);
//            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static LoginController createLoginUseCase(
            LoginViewModel loginViewModel,
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ViewResponseViewModel viewResponseViewModel,
            LoginUserDataInterface loginUserDataInterface) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, searchViewModel,
                viewResponseViewModel, viewManagerModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                loginUserDataInterface, loginOutputBoundary);

        return new LoginController(loginInteractor);

    }


}

