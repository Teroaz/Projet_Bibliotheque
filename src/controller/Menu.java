package controller;

import view.PanelMenu;
import view.PanelNavigation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu implements ActionListener {

    private PanelMenu panelMenu;
    private PanelNavigation panelNavigation;

    public Menu() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        panelNavigation.markAsActive(button);

        ArrayList<JButton> navButtons = panelNavigation.getButtons();

        if (button == navButtons.get(navButtons.size() - 1)) {
            PanelSwitcher.switchToConnexion();
        }
    }

    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public void setPanelMenu(PanelMenu panelMenu) {
        this.panelMenu = panelMenu;
    }

    public PanelNavigation getPanelNavigation() {
        return panelNavigation;
    }

    public void setPanelNavigation(PanelNavigation panelNavigation) {
        this.panelNavigation = panelNavigation;
    }


}
