package view.menu.etudiant;

import controller.Catalogue;
import controller.GestionEtudiant;
import model.Emprunt;
import model.Etudiant;
import model.Livre;
import model.Reservation;
import view.menu.catalogue.JTableCatalogue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleEtudiant extends DefaultTableModel {
    private final String[] intitulesColonnes = {"ID", "Nom", "Prénom", "Nb d'emprunts", "Nb réser"};
    private final GestionEtudiant etuController;

    private Collection<Etudiant> etudiants;

    int nbEtudiants;

    public ModeleEtudiant(GestionEtudiant etuController) {

        this.etuController = etuController;

        etudiants = Etudiant.liste.values();

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        updateEtudiant(etudiants);
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }

    public void updateEtudiant(Collection<Etudiant> etudiants) {

        ArrayList<Etudiant> etudiantArr = new ArrayList<>(etudiants);
        nbEtudiants = etudiantArr.size();
        setRowCount(nbEtudiants);

        for (int i = 0; i < nbEtudiants; i++) {
            Etudiant etudiant = etudiantArr.get(i);

            setValueAt(etudiant.getId(), i, 0);
            setValueAt(etudiant.getNom(), i, 1);
            setValueAt(etudiant.getPrenom(), i, 2);
            setValueAt(Emprunt.getEmpruntEtudiant(etudiant.getId()).size(), i, 3);
            setValueAt(Reservation.getReservationEtudiant(etudiant.getId()).size(), i, 4);
        }
    }

    public Class getColumnClass(int i) {
        if (i == 0 || i == 3 || i == 4)
            return Integer.class;
        return String.class;
    }

    public GestionEtudiant getEtuController() {
        return etuController;
    }

    public Collection<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void registerListeners(JTableEtudiant tableEtudiant) {
        ListSelectionModel selectionModel = tableEtudiant.getSelectionModel();
        selectionModel.addListSelectionListener(e -> etuController.onTableSelection(selectionModel));
    }

    public Etudiant getEtudiantByRow(int row) {
        return Etudiant.getById((Integer) getValueAt(row, 0));
    }
}
