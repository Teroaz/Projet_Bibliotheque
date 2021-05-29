package view.menu.emprunts_reservations;

import model.Emprunt;
import org.junit.jupiter.params.converter.DefaultArgumentConverter;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ModeleEmprunt extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "Exemplaire", "Etudiant", "Date", "Date retour"};

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
