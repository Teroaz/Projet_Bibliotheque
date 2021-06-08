package controller;

import model.Etudiant;
import model.design.Couleurs;
import view.menu.etudiant.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GestionEtudiant implements ActionListener, KeyListener {

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        PanelRechercheEtudiant panelRechercheEtudiant = panelEtudiant.getPanelRechercheEtudiant();

        if (e.getSource() == panelRechercheEtudiant.getTexteRecherche()) {
            String saisieRecherche = panelRechercheEtudiant.getTexteRecherche().getText();

            ArrayList<Etudiant> etudiants;

            if (panelRechercheEtudiant.getChoixRecherche().getSelectedIndex() == 0) {
                etudiants = Etudiant.rechercherEtudiants(saisieRecherche);
            } else {
                etudiants = Etudiant.searchByMail(saisieRecherche);
            }

            panelRechercheEtudiant.getTexteRecherche().setForeground(etudiants == null ? Couleurs.ROUGE.getCouleur() : null);
            panelEtudiant.getModeleEtudiant().updateEtudiant(etudiants == null ? Etudiant.liste.values() : etudiants);

        }
    }
}
