package interface_adapter.profile;

import use_case.toProfile.ProfileInteractor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {
    private final ProfileInteractor profileInteractor;

    public ProfileController(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle user interactions and trigger the interactor
        // Update the ProfileViewState and call the interactor
    }
}

