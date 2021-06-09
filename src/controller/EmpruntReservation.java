package controller;

import model.Emprunt;
import model.Etudiant;
import model.Reservation;
import model.design.Couleurs;
import view.menu.emprunts_reservations.PanelEmpruntReservation;
import view.menu.emprunts_reservations.emprunt.DialogAjoutEmprunt;
import view.menu.emprunts_reservations.emprunt.DialogModificationEmprunt;
import view.menu.emprunts_reservations.emprunt.DialogSuppressionEmprunt;
import view.menu.emprunts_reservations.emprunt.ModeleEmprunt;
import view.menu.emprunts_reservations.reservation.DialogAjoutReservation;
import view.menu.emprunts_reservations.reservation.DialogSuppressionReservation;
import view.menu.emprunts_reservations.reservation.ModeleReservation;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class EmpruntReservation implements ActionListener, MouseListener, KeyListener {

    int selectedIndex;

    private final PanelEmpruntReservation panelEmpruntReservation;
    private static EmpruntReservation instance;

    public EmpruntReservation() {
        instance = this;
        panelEmpruntReservation = new PanelEmpruntReservation();
    }

    public static EmpruntReservation getInstance() {
        return instance;
    }

    public PanelEmpruntReservation getPanelEmpruntReservation() {
        return panelEmpruntReservation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ajoutEmp" -> new DialogAjoutEmprunt();
            case "ajoutRes" -> new DialogAjoutReservation();
            case "modificationEmp" -> new DialogModificationEmprunt();
            case "suppressionEmp" -> new DialogSuppressionEmprunt();
            case "suppressionRes" -> new DialogSuppressionReservation();
        }
    }

//    public void onTableSelection(ListSelectionModel selectionModel) {
//
//        if (selectionModel.isSelectionEmpty()) {
//            selectionModel.clearSelection();
//            selectedIndex = 0;
//            panelEmpruntReservation.getPanelEmprunt().get.setEnabled(false);
//            panelEmpruntReservation.getBoutonSuppression().setEnabled(false);
//            return;
//        }
//
//        System.out.println(selectionModel.toString());
//        int newSelectedIndex = selectionModel.getMinSelectionIndex();
//
//        if (selectedIndex == newSelectedIndex) return;
//
//        selectedIndex = newSelectedIndex;
//        panelEmpruntReservation.getBoutonModif().setEnabled(true);
//        panelEmpruntReservation.getBoutonSuppression().setEnabled(true);
//        System.out.println(getTableSelectedEtudiant());
//    }
//
//    public Emprunt getTableSelectedEmprunt() {
//        return panelEmpruntReservation.getPanelEmprunt().getM().getEtudiantByRow(selectedIndex);
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getSelectionModel().isSelectionEmpty()) {
//            int rowEmprunt = panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getSelectedRow();
//            ModeleEmprunt modeleEmprunt = new ModeleEmprunt();
//            Emprunt emprunt = modeleEmprunt.getEmpruntByRow(rowEmprunt);
//            System.out.println(emprunt.toString());
//            panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getValueAt(rowEx, 1);
//            panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getValueAt(rowEx, 2);
//            panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getValueAt(rowEx, 3);
//            panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getValueAt(rowEx, 4);
//            panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getValueAt(rowEx, 5);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == panelEmpruntReservation.getPanelRecherche().getChoixEmpruntReservation()) {
            String choix = panelEmpruntReservation.getPanelRecherche().getChoixEmpruntReservation().getSelectedItem().toString();
            if (choix == "emprunt")
                panelEmpruntReservation.getPanelRecherche().getLabelRecherche().setText("Je recherche un ");
            if (choix == "réservation")
                panelEmpruntReservation.getPanelRecherche().getLabelRecherche().setText("Je recherche une ");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == panelEmpruntReservation.getPanelRecherche().getTexteRecherche()) {
            String recherche = panelEmpruntReservation.getPanelRecherche().getTexteRecherche().toString();
            String choix = panelEmpruntReservation.getPanelRecherche().getChoixEmpruntReservation().getSelectedItem().toString();

            TreeSet <Emprunt> emprunts = new TreeSet<>();
            TreeSet <Reservation> reservations = new TreeSet<>();

            if (choix.equals("emprunt")) {
                emprunts = Emprunt.rechercheByTitre(recherche);
                panelEmpruntReservation.getPanelEmprunt().getTableEmprunt().getModeleEmprunt().updateEmprunts(emprunts == null ? Emprunt.emprunt : emprunts);
            }
            else {
                reservations = Reservation.rechercheByTitre(recherche);
                panelEmpruntReservation.getPanelReservation().getTableReservation().getModeleReservation().updateReservations(reservations == null ? Reservation.reservation : reservations);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
