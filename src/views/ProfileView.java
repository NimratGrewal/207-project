package views;

import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeleteState;
import interface_adapter.delete.DeleteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "delete";
    private final DeleteViewModel deleteViewModel;
    private final DeleteController deleteController;

    private final JButton delete;

    public ProfileView(DeleteViewModel deleteViewModel, DeleteController deleteController) {
        this.deleteViewModel = deleteViewModel;
        this.deleteController = deleteController;

        deleteViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(deleteViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        delete = new JButton(deleteViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)) {
                    DeleteState state = deleteViewModel.getState();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogueResult = JOptionPane.showConfirmDialog(delete,
                            "Are you sure you want to delete Response: " + state.getResponseId() + "?", "Warning", dialogButton);

                    if (dialogueResult == JOptionPane.YES_OPTION) {
                        DeleteState deleteState = deleteViewModel.getState();
                        deleteController.execute(deleteState.getResponseId());
                    }
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteState state = (DeleteState) evt.getNewValue();
        JOptionPane.showConfirmDialog(this, "Response" + state.getResponseId() + "was deleted!");
    }
}

