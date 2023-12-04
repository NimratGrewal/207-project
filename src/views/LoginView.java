package views;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private JTextField usernameInputField = new JTextField(15);
    private JTextField passwordInputField = new JPasswordField(15);

    private final LoginController loginController;
    private final LoginViewModel loginViewModel;
    private final JButton loginButton;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel){
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(loginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(loginViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(loginViewModel.PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();
        loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(loginButton);

        this.add(buttons);

        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loginButton)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        String text = passwordInputField.getText() + e.getKeyChar();
                        currentState.setPassword(text);
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(loginButton)) {
            String username = usernameInputField.getText();
            String password = passwordInputField.getText();

            loginController.execute(username, password);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
