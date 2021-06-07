package controller;

import model.Etudiant;
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
        switch (e.getActionCommand()) {
            case "ajout" -> new DialogAjoutEtudiant();
            case "modification" -> new DialogModificationEtudiant();
            case "suppression" -> new DialogSuppressionEtudiant();
        }
    }

    public void onTableSelection(ListSelectionModel selectionModel) {

        if (selectionModel.isSelectionEmpty()) {
            selectionModel.clearSelection();
            selectedIndex = 0;
            panelEtudiant.getBoutonModif().setEnabled(false);
            panelEtudiant.getBoutonSuppression().setEnabled(false);
            return;
        }

        int newSelectedIndex = selectionModel.getMinSelectionIndex();

        selectedIndex = newSelectedIndex;
        panelEtudiant.getBoutonModif().setEnabled(true);
        panelEtudiant.getBoutonSuppression().setEnabled(true);
    }

    public Etudiant getTableSelectedEtudiant() {
        return panelEtudiant.getModeleEtudiant().getEtudiantByRow(selectedIndex);
    }
}
