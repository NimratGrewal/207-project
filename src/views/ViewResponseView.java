package views;

import interface_adapter.home.HomeController;
import interface_adapter.view_response.ViewResponseState;
import interface_adapter.view_response.ViewResponseViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewResponseView extends JPanel implements PropertyChangeListener {

    public final String viewName = "view response";

    public final ViewResponseViewModel viewResponseViewModel;
    private final HomeController homeController;

    private final JLabel promptText;
    private final ImageIcon albumCover;
    private final JLabel songTitle;
    private final JLabel albumName;
    private final JLabel artistNames;
    final JButton reset;

    public ViewResponseView(ViewResponseViewModel viewResponseViewModel, HomeController homeController) {
        this.viewResponseViewModel = viewResponseViewModel;
        this.homeController = homeController;

        JLabel title = new JLabel("Home Screen");
        title.setHorizontalAlignment(JLabel.CENTER);

        promptText = new JLabel();
        promptText.setHorizontalAlignment(JLabel.CENTER);
        JLabel albumCoverContainer = new JLabel();

        albumCover = new ImageIcon();
        albumCoverContainer.setIcon(albumCover);
        albumCoverContainer.setHorizontalAlignment(JLabel.CENTER);

        songTitle = new JLabel();
        songTitle.setHorizontalAlignment(JLabel.CENTER);

        albumName = new JLabel();
        albumName.setHorizontalAlignment(JLabel.CENTER);

        artistNames = new JLabel();
        artistNames.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttons = new JPanel();
        reset = new JButton(viewResponseViewModel.RESET_BUTTON_LABEL);
        buttons.add(reset);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        reset.addActionListener(
                e -> {
                    if (e.getSource().equals(reset)) {
                        homeController.execute();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(promptText);
        this.add(albumCoverContainer);
        this.add(songTitle);
        this.add(albumName);
        this.add(artistNames);
        this.add(buttons);

        viewResponseViewModel.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof ViewResponseState state) {
            System.out.println(state);

            System.out.println(state.getSongName());

            System.out.println(state.getArtistNames());

            System.out.println(state.getSongName());
            promptText.setText(state.getPromptText());
            albumCover.setImage(state.getAlbumCover().getImage());
            songTitle.setText(state.getSongName());
            albumName.setText(state.getAlbumName());
            artistNames.setText(state.getArtistNames());
        }
    }
}
