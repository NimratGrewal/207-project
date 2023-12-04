package use_case.signup;
import entities.CommonUserFactory;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SignupInteractorTest {

    private SignupUserDataInterface userDataAccess;
    private SignupOutputBoundary mockPresenter;
    private SignupInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(SignupUserDataInterface.class);
        mockPresenter = mock(SignupOutputBoundary.class);
        CommonUserFactory userFactory = new CommonUserFactory();
        interactor = new SignupInteractor(userDataAccess, mockPresenter, userFactory);
    }

    @Test
    void testExecute_SuccessfulSignup() {
        SignupInputData signupInputData = new SignupInputData("validUsername", "validPassword", "validPassword");

        when(userDataAccess.existsByName("validUsername")).thenReturn(false);

        interactor.execute(signupInputData);

        verify(userDataAccess, times(1)).existsByName("validUsername");
        verify(userDataAccess, times(1)).save(any(User.class));
        verify(mockPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
    }

    @Test
    void testExecute_UserExists() {
        SignupInputData signupInputData = new SignupInputData("validUsername", "validPassword", "validPassword");

        when(userDataAccess.existsByName("validUsername")).thenReturn(true);

        interactor.execute(signupInputData);

        verify(userDataAccess, times(1)).existsByName("validUsername");
        verify(mockPresenter, times(1)).prepareFailView("User already exists.");
    }

    @Test
    void testExecute_PasswordMismatch() {
        SignupInputData signupInputData = new SignupInputData(
                "validUsername", "validPassword11", "validPassword22");

        when(userDataAccess.existsByName("validUsername")).thenReturn(false);

        interactor.execute(signupInputData);

        verify(userDataAccess, times(1)).existsByName("validUsername");
        verify(mockPresenter, times(1)).prepareFailView("Passwords don't match.");
    }

    @Test
    void testExecute_UsernameLength() {
        SignupInputData signupInputData = new SignupInputData("user", "password", "password");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_BothPasswordLength() {
        SignupInputData signupInputData = new SignupInputData("username", "pass", "pass");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).save(any(User.class));
        verify(userDataAccess, never()).existsByName(any());
    }

    @Test
    void testExecute_UsernameAndPasswordLength() {
        SignupInputData signupInputData = new SignupInputData("user", "pass", "pass");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_EmptyUsername() {
        SignupInputData signupInputData = new SignupInputData("", "password", "password");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_EmptyPassword() {
        SignupInputData signupInputData = new SignupInputData("username", "", "");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_EmptyUsernameAndPassword() {
        SignupInputData signupInputData = new SignupInputData("", "", "");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password must be at least 6 characters long.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_UsernameContainsSpace() {
        SignupInputData signupInputData = new SignupInputData("user with space", "password", "password");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password cannot contain spaces.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_PasswordContainsSpace() {
        SignupInputData signupInputData = new SignupInputData("username", "pass word", "pass word");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password cannot contain spaces.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }

    @Test
    void testExecute_UsernameAndPasswordContainsSpace() {
        SignupInputData signupInputData = new SignupInputData("user with space", "pass word", "pass word");

        interactor.execute(signupInputData);

        verify(mockPresenter, times(1)).prepareFailView("Username and password cannot contain spaces.");
        verify(userDataAccess, never()).existsByName(any());
        verify(userDataAccess, never()).save(any(User.class));
    }
}