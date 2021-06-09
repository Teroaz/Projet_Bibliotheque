package view.menu.catalogue.exemplaires;

import controller.Catalogue;
import model.Exemplaire;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleExemplaires extends DefaultTableModel {

    private final String[] intitulesColonnes = {"ID", "Titre", "Etat", "Emprunté"};
    private final Catalogue catController;

    private final Collection<Exemplaire> exemplaires;

    int nbExemplaires;

    public ModeleExemplaires(Collection<Exemplaire> exemplaires) {

        catController = Catalogue.getInstance();

        this.exemplaires = exemplaires;

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        updateExemplaires(exemplaires);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    private void updateExemplaires(Collection<Exemplaire> exemplaires) {

        ArrayList<Exemplaire> exemplaireArr = new ArrayList<>(exemplaires);
        nbExemplaires = exemplaires.size();
        setRowCount(nbExemplaires);

        for (int i = 0; i < nbExemplaires; i++) {
            Exemplaire exemplaire = exemplaireArr.get(i);

            setValueAt(exemplaire.getId(), i, 0);
            setValueAt(exemplaire.getLivre().getTitre(), i, 1);
            setValueAt(exemplaire.getEtat().getLabel(), i, 2);
            setValueAt(exemplaire.estEmprunte() ? "Oui" : "Non", i, 3);
        }
    }

    public Class getColumnClass(int i) {
        if (i == 0) return Integer.class;
        return String.class;
    }

    public void registerListeners(JTableExemplaires jTableExemplaires) {
        ListSelectionModel selectionModel = jTableExemplaires.getSelectionModel();
        selectionModel.addListSelectionListener(e -> catController.onTableSelection(selectionModel));
    }

    public Catalogue getCatController() {
        return catController;
    }

    public Collection<Exemplaire> getExemplaires() {
        return exemplaires;
    }
}
