package views;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout viewsCardLayout;
    private final JPanel views;
    private final JPanel signUpLoginViews;
    private final CardLayout signUpLoginCardLayout;
    private final JTabbedPane loggedInViews;

    private final JPanel promptView;
    private final CardLayout promptViewsCardLayout;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(CardLayout viewsCardLayout, JPanel views, JPanel signUpLoginViews, CardLayout signUpLoginCardLayout, JTabbedPane loggedInViews, JPanel promptView, CardLayout promptViewsCardLayout, ViewManagerModel viewManagerModel) {
        this.viewsCardLayout = viewsCardLayout;
        this.views = views;
        this.signUpLoginViews = signUpLoginViews;
        this.signUpLoginCardLayout = signUpLoginCardLayout;
        this.loggedInViews = loggedInViews;
        this.promptView = promptView;
        this.promptViewsCardLayout = promptViewsCardLayout;

        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            switch (viewModelName) {
                case "search", "view response" -> {
                    viewsCardLayout.show(views, loggedInViews.getName());
                    loggedInViews.setSelectedIndex(loggedInViews.indexOfTab(promptView.getName()));
                    promptViewsCardLayout.show(promptView, viewModelName);
                }
                case "feed", "profile" -> {
                    viewsCardLayout.show(views, loggedInViews.getName());
                    loggedInViews.setSelectedIndex(loggedInViews.indexOfTab(viewModelName));
                }
                case "start", "log in", "sign up" -> {
                    viewsCardLayout.show(views, signUpLoginViews.getName());
                    signUpLoginCardLayout.show(signUpLoginViews, viewModelName);
                }
            }
        }
    }
}
