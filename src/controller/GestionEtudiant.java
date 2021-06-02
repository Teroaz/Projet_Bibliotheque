package controller;

import model.Etudiant;
import model.Livre;
import view.menu.catalogue.PanelCatalogue;
import view.menu.emprunts_reservations.emprunt.DialogAjoutEmprunt;
import view.menu.emprunts_reservations.emprunt.DialogModificationEmprunt;
import view.menu.emprunts_reservations.reservation.DialogAjoutReservation;
import view.menu.etudiant.DialogAjoutEtudiant;
import view.menu.etudiant.DialogModificationEtudiant;
import view.menu.etudiant.DialogSuppressionEtudiant;
import view.menu.etudiant.PanelEtudiant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionEtudiant implements ActionListener {

    private int selectedIndex = 0;
    private static GestionEtudiant instance;

    private final PanelEtudiant panelEtudiant;

    public GestionEtudiant() {
        instance = this;
        panelEtudiant = new PanelEtudiant();
    }

    public static GestionEtudiant getInstance() {
        return instance;
    }

    public PanelEtudiant getPanelEtudiant() {
        return panelEtudiant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ajout")) {
            new DialogAjoutEtudiant();
        }
        if (e.getActionCommand().equals("modification")) {
            new DialogModificationEtudiant();
        }
        if (e.getActionCommand().equals("suppression")) {
            new DialogSuppressionEtudiant();
        }
    }

    public void onTableSelection(ListSelectionModel selectionModel) {

        if (selectionModel.isSelectionEmpty()) {
            selectionModel.clearSelection();
            selectedIndex = 0;
            panelEtudiant.getBoutonModif().setEnabled(false);
            return;
        }

        System.out.println(selectionModel.toString());
        int newSelectedIndex = selectionModel.getMinSelectionIndex();

        if (selectedIndex == newSelectedIndex) return;

        selectedIndex = newSelectedIndex;
        panelEtudiant.getBoutonModif().setEnabled(true);
        System.out.println(getTableSelectedEtudiant());
    }

    public Etudiant getTableSelectedEtudiant() {
        return panelEtudiant.getModeleEtudiant().getEtudiantByRow(selectedIndex);
    }
}
