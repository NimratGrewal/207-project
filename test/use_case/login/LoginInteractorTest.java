package use_case.login;

import app.LoginUseCaseFactory;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import org.mockito.Mock;
class LoginInteractorTest {
    private LoginUserDataInterface userDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor interactor;
    private User user;
    private Song song;
    private Prompt prompt;
    @BeforeEach
    void setup(){
        userDataAccess = mock(LoginUserDataInterface.class);
        mockPresenter = mock(LoginOutputBoundary.class);
        song = mock(Song.class);
        user = mock(User.class);
        prompt = mock(Prompt.class);

        interactor = new LoginInteractor(userDataAccess, mockPresenter);
    }

    @Test

    void test_execute_login(){
        LoginInputData loginInputData = new LoginInputData("validUsername", "validPassword");
        when(userDataAccess.existsByName("validUsername")).thenReturn(true);
        when(userDataAccess.getUsername("validUsername").getPassword()).thenReturn("validPassword");
        when(userDataAccess.getUsername("validUsername")).thenReturn(user);
        when(userDataAccess.getCurrentPrompt()).thenReturn(prompt);
        when(user.getHistory().containsKey(prompt.getPromptId())).thenReturn(true);
        interactor.execute(loginInputData);
        verify(mockPresenter, times(1)).prepareLoggedInView(any(LoginOutputData.class), any(Song.class
        ));

    }

    void test_mismatch_password(){
        LoginInputData loginInputData = new LoginInputData("validUsername", "hello");
        when(userDataAccess.existsByName("validUsername")).thenReturn(true);
        when(userDataAccess.getUsername("validUsername").getPassword()).thenReturn("aye");
        interactor.execute(loginInputData);
        verify(mockPresenter, times(1)).prepareFailView("Passwords don't match.");
    }
}