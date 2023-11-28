package views;

import interface_adapter.home.HomeController;
import interface_adapter.view_response.ViewResponseViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewResponseView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "view response view";

    public final ViewResponseViewModel viewResponseViewModel;

    private final HomeController homeController;

    final JButton change;

    public ViewResponseView(ViewResponseViewModel viewResponseViewModel, HomeController homeController) {
        this.viewResponseViewModel = viewResponseViewModel;
        this.homeController = homeController;

        JLabel title = new JLabel("Home Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        change = new JButton(viewResponseViewModel.CHANGE_BUTTON_LABEL);
        buttons.add(change);

        change.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(change)) {
                            homeController.execute();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
