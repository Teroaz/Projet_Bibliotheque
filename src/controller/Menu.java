package controller;

import view.menu.PanelMenu;
import view.menu.PanelNavigation;
import view.menu.emprunts_reservations.PanelEmpruntReservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu implements ActionListener {

    private PanelMenu panelMenu;
    private PanelNavigation panelNavigation;

    public Menu() {
        panelNavigation = new PanelNavigation(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        panelNavigation.markAsActive(button);

        ArrayList<JButton> navButtons = panelNavigation.getButtons();

        if (button == navButtons.get(navButtons.size() - 1)) {
            PanelSwitcher.switchToConnexion();
        }

        if (button == navButtons.get(navButtons.size() - 2)) {
            PanelSwitcher.switchToEmpruntReservation();
        }

        if (button == navButtons.get(navButtons.size() -3)) {
            PanelSwitcher.switchToMenu();
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
