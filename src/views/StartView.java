package views;

import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Start View";
    public final String viewName = "home view";

    private final StartController startController;

    private final StartViewModel startViewModel;

    private final JButton signUp;
    private final JButton login;

    public StartView(StartController controller, StartViewModel startViewModel){
        this.startController = controller;
        this.startViewModel = startViewModel;
        signupViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        signUp = new JButton(StartViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        login = new JButton(StartViewModel.LOGIN_BUTTON_LABEL);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
