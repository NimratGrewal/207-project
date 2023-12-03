package use_case.home;

import interface_adapter.home.HomeController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeInteractorTest {

    @Test
    void execute() {

        // used the mockito library for this test to see is prepareSuccessView ran
        // covers 100% of HomeTnteractor test with coverage
        HomeOutputBoundary mockPresenter = mock(HomeOutputBoundary.class);

        HomeInteractor interactor = new HomeInteractor(mockPresenter);

        HomeController controller = new HomeController(interactor);

        // Call the method under test
        controller.execute();

        // Verify that the expected method was called on the mockPresenter
        verify(mockPresenter, times(1)).prepareSuccessView();
    }
}