package view.menu.catalogue;

import controller.Catalogue;
import model.Livre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleCatalogue extends DefaultTableModel {

    private final String[] intitulesColonnes = {"ID", "Titre", "Auteur", "Exemplaires"};
    private final Catalogue catController;

    private Collection<Livre> livres;

    int nbLivres;

    public ModeleCatalogue(Catalogue catController) {

        this.catController = catController;

        livres = Livre.catalogue.values();

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        updateCatalogue(livres);
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }

    public void updateCatalogue(Collection<Livre> livres) {

        ArrayList<Livre> livresArr = new ArrayList<>(livres);
        nbLivres = livresArr.size();
        setRowCount(nbLivres);

        for (int i = 0; i < livresArr.size(); i++) {
            Livre livre = livresArr.get(i);

            setValueAt(livre.getId(), i, 0);
            setValueAt(livre.getTitre(), i, 1);
            setValueAt(livre.getAuteur().auteurNP(), i, 2);
            setValueAt(livre.getExemplaires().size(), i, 3);
        }
    }

    public Class getColumnClass(int i) {
        if (i == 0 || i == 3)
            return Integer.class;
        return String.class;
    }

    public Catalogue getCatController() {
        return catController;
    }

    public Collection<Livre> getLivres() {
        return livres;
    }

    public void registerListeners(JTableCatalogue tableCatalogue) {
        ListSelectionModel selectionModel = tableCatalogue.getSelectionModel();
        selectionModel.addListSelectionListener(e -> catController.onTableSelection(selectionModel));
    }

    public Livre getLivreByRow(int row) {
        return Livre.getLivre((Integer) getValueAt(row, 0));
    }
}
