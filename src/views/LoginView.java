package views;
import interface_adapter.login.LoginController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private JTextField login = new JTextField();

    private JTextField password = new JPasswordField();
    private JTextField signup = new JTextField();

    private final LoginController loginController;
    private final LoginView loginView;
    private final JButton signUp;
    private final JButton cancel;

    LoginView(LoginController loginController, LoginView loginView){
        this.loginController = loginController;
        this.loginView = loginView;
        JPanel loginPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1000);
        loginPanel.add(login);
        loginPanel.add(password);
        loginPanel.add(signup);
        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
    }

    public String getUsername(){
        return login.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
