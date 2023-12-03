package views;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private LoginViewModel loginViewModel;

    private LoginController loginController;
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton login;
    final JButton cancel;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel){
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        JPanel loginPanel = new JPanel();
        this.setSize(1500, 1000);
        JLabel title = new JLabel("Login Screen");

        JTextField usernameInputField = new JTextField();
        JPasswordField passwordInputField = new JPasswordField();

        JPanel buttons = new JPanel();
        loginPanel.add(buttons);
        login = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);
        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginState loginState = loginViewModel.getState();

                loginController.execute(
                        loginState.getUsername(),
                        loginState.getPassword()
                );
            }
        });

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState loginState = loginViewModel.getState();
                loginState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(loginState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState loginState = loginViewModel.getState();
                loginState.setPassword(passwordInputField.getText() + e.getKeyChar());
                loginViewModel.setState(loginState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
