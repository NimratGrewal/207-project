package views;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "start";

    private final LoginViewModel loginViewModel;

    private final SignupViewModel signupViewModel;

    private final ViewManagerModel viewManagerModel;
    private final JButton signUp;
    private final JButton login;

    public StartView(LoginViewModel loginViewModel, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel){
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;

        JPanel buttons = new JPanel();
        signUp = new JButton("Sign Up");
        buttons.add(signUp);
        login = new JButton("Log In");
        buttons.add(login);

        add(buttons);

        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SignupState signupState = signupViewModel.getState();
                        viewManagerModel.setActiveView(signupViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                        //switch to signup view

                    }
                }
        );

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LoginState loginState = loginViewModel.getState();
                        viewManagerModel.setActiveView(loginViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        this.add(signUp);
        this.add(login);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}