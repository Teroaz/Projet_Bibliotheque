package view.menu.emprunts_reservations.emprunt;

import model.Emprunt;
import model.Exemplaire;
import model.Livre;
import model.Reservation;
import utils.DateUtils;

import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;

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

    public Emprunt getEmpruntByRow(int row) {
        Date date;
        try {
            String stringDate = (String) getValueAt(row, 3);
            date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            int idEtudiant = (Integer) getValueAt(row, 2);
            int idLivre = Livre.getLivre((String) getValueAt(row, 0)).getId();
            return Emprunt.getEmprunt(date, idEtudiant, idLivre);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
