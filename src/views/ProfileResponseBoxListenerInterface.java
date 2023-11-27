package views;

import interface_adapter.profile.ProfileState;

import java.util.UUID;

public interface ProfileResponseBoxListenerInterface {
    void onDeleteAction(ProfileState state);
    ProfileState beforeDeleteAction(UUID responseId);
}
