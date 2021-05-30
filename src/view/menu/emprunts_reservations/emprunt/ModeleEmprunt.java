package view.menu.emprunts_reservations.emprunt;

import model.Emprunt;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.Date;

public class ModeleEmprunt extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "Exemplaire", "N° étudiant", "Date", "Date retour"};

    int nbEmprunts;

    public ModeleEmprunt() {
        Collection<Emprunt> emprunts = Emprunt.emprunt.values();

        nbEmprunts = emprunts.size();

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        setRowCount(nbEmprunts);
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }

    public Class getColumnClass(int i) {
        if (i == 0)
            return String.class;
        else if (i == 1 || i == 2)
            return Integer.class;
        return Date.class;
    }

}
