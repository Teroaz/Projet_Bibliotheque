package controller;

import view.menu.PanelMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    private final PanelMenu panelMenu;

    private static Menu instance;
    private final Catalogue catalogue;
    private final EmpruntReservation empruntReservation;

    public Menu() {
        instance = this;

        panelMenu = new PanelMenu();

        catalogue = new Catalogue();
        empruntReservation = new EmpruntReservation();

        panelMenu.setBodyPanel(catalogue.getPanelCatalogue());
    }

    public static Menu getInstance() {
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        panelMenu.getPanelNavigation().markAsActive(button);
        String text = button.getText();

        if (text.equals("Déconnexion")) {
            PanelSwitcher.switchToConnexion();
        } else if (text.equals("Livres & Exemplaires")) {
            panelMenu.setBodyPanel(catalogue.getPanelCatalogue());
        } else if (text.equals("Emprunts & Réservations")) {
            panelMenu.setBodyPanel(empruntReservation.getPanelEmpruntReservation());
        }
    }

    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public EmpruntReservation getEmpruntReservation() {
        return empruntReservation;
    }
}
