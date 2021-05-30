package view.menu.emprunts_reservations.emprunt;

import model.Emprunt;
import utils.DateUtils;

import javax.swing.table.DefaultTableModel;
import java.util.*;

public class ModeleEmprunt extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "Exemplaire", "N° étudiant", "Date", "Date retour"};
    private TreeSet<Emprunt> emprunts;
    int nbEmprunts;

    public ModeleEmprunt() {
        emprunts = Emprunt.emprunt;

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        updateEmprunts(emprunts);
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }

    public void updateEmprunts(TreeSet<Emprunt> emprunts) {

        Iterator <Emprunt> iterator = emprunts.iterator();
        nbEmprunts = emprunts.size();
        setRowCount(nbEmprunts);

        if (!emprunts.isEmpty()) {
            for (int i = 0; i < nbEmprunts; i++) {
                Emprunt emprunt = iterator.next();

                setValueAt(emprunt.getExemplaire().getLivre().getTitre(), i, 0);
                setValueAt(emprunt.getExemplaire().getId(), i, 1);
                setValueAt(emprunt.getEtudiant().getId(), i, 2);
                setValueAt(DateUtils.toString(emprunt.getDate_emp()), i, 3);
                setValueAt(DateUtils.toString(emprunt.getDate_fin_emp()), i, 4);
            }
        }
    }

    public Class getColumnClass(int i) {
        if (i == 1 || i == 2)
            return Integer.class;
        return String.class;
    }

}
