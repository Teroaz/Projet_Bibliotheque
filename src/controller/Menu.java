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
    private final GestionEtudiant gestionEtudiant;
    private EspaceEtudiant monEspaceEtudiant;

    public Menu() {
        instance = this;

        panelMenu = new PanelMenu();

        catalogue = new Catalogue();
        empruntReservation = new EmpruntReservation();
        gestionEtudiant = new GestionEtudiant();

        if (Connexion.isAdminMode()) {
            panelMenu.setBodyPanel(gestionEtudiant.getPanelEtudiant());
        } else {
            monEspaceEtudiant = new EspaceEtudiant();
            panelMenu.setBodyPanel(monEspaceEtudiant.getPanelCompte());
        }
    }

    public static Menu getInstance() {
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        panelMenu.getPanelNavigation().markAsActive(button);
        String text = button.getText();

        switch (text) {
            case "Déconnexion" -> PanelSwitcher.switchToConnexion();
            case "Livres & Exemplaires", "Catalogue" -> panelMenu.setBodyPanel(catalogue.getPanelCatalogue());
            case "Emprunts & Réservations" -> panelMenu.setBodyPanel(empruntReservation.getPanelEmpruntReservation());
            case "Étudiants" -> panelMenu.setBodyPanel(gestionEtudiant.getPanelEtudiant());
            case "Mon espace" -> panelMenu.setBodyPanel(monEspaceEtudiant.getPanelCompte());
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

    public EspaceEtudiant getMonEspaceEtudiant() {
        return monEspaceEtudiant;
    }

    public GestionEtudiant getGestionEtudiant() {
        return gestionEtudiant;
    }
}
