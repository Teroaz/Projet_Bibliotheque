package view.menu;

import controller.Menu;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    private final Menu menuController;
    private final PanelNavigation panelNavigation;

    private JPanel currentBodyPanel;

    public PanelMenu() {
        menuController = Menu.getInstance();
        setLayout(new BorderLayout());

        panelNavigation = new PanelNavigation();

        add(panelNavigation, BorderLayout.NORTH);
        panelNavigation.markAsActive(panelNavigation.getButtons().get(1));
    }

    public void setBodyPanel(JPanel panel) {
        if (currentBodyPanel != null) {
            remove(currentBodyPanel);
        }

        currentBodyPanel = panel;
        add(panel, BorderLayout.CENTER);

        FenetreBibliotheque.getInstance().getContentPane().revalidate();
        repaint();
    }

    public Menu getMenuController() {
        return menuController;
    }

    public PanelNavigation getPanelNavigation() {
        return panelNavigation;
    }
}
