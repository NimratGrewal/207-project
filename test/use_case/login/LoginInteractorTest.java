package use_case.login;

import app.LoginUseCaseFactory;
import entities.CommonUserFactory;
import entities.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataInterface;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import org.mockito.Mock;
class LoginInteractorTest {
    private LoginUserDataInterface userDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor interactor;

    private Song song;
    @BeforeEach
    void setup(){
        userDataAccess = mock(LoginUserDataInterface.class);
        mockPresenter = mock(LoginOutputBoundary.class);
        song = mock(Song.class);
        interactor = new LoginInteractor(userDataAccess, mockPresenter);
        LoginUseCaseFactory loginUseCaseFactory = new LoginUseCaseFactory();
    }

    @Test

    void test_execute_login(){
        LoginInputData loginInputData = new LoginInputData("validUsername", "validPassword");
        when(userDataAccess.existsByName("validUsername")).thenReturn(true);
    }
    @Test

    void test_mismatch_password(){
        LoginInputData loginInputData = new LoginInputData("validUsername", "hello");
        when(userDataAccess.existsByName("validUsername")).thenReturn(true);
        when(userDataAccess.getUsername("validUsername").getPassword()).thenReturn("aye");
        interactor.execute(loginInputData);
        verify(mockPresenter, times(1)).prepareFailView("Passwords don't match.");
    }
}