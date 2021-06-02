package controller;

import view.menu.PanelMenu;
import view.menu.mon_compte.PanelCompte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    private final PanelMenu panelMenu;

    private static Menu instance;
    private final Catalogue catalogue;
    private final EmpruntReservation empruntReservation;
    private final GestionEtudiant gestionEtudiant;

    public Menu() {
        instance = this;

        panelMenu = new PanelMenu();

        catalogue = new Catalogue();
        empruntReservation = new EmpruntReservation();
        gestionEtudiant = new GestionEtudiant();

        panelMenu.setBodyPanel(/*new PanelCompte());*/catalogue.getPanelCatalogue());
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
        } else if (text.equals("Étudiants")) {
            panelMenu.setBodyPanel(gestionEtudiant.getPanelEtudiant());
        } else if (text.equals("Mon espace")) {
            panelMenu.setBodyPanel(new PanelCompte(Connexion.getConnectedStudent()));
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
