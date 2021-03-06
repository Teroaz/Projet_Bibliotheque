package view.menu.emprunts_reservations.reservation;

import model.Emprunt;
import model.Etudiant;
import model.Livre;
import model.Reservation;
import utils.DateUtils;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class ModeleReservation extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "N° étudiant", "Date", "Date fin"};
    TreeSet<Reservation> reservations;
    int nbReservations;

    public ModeleReservation() {
        reservations = Reservation.reservation;

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        updateReservations(reservations);
    }

    public void updateReservations(TreeSet<Reservation> reservations) {

        Iterator <Reservation> iterator = reservations.iterator();
        nbReservations = reservations.size();
        setRowCount(nbReservations);

        if (!reservations.isEmpty()) {
            for (int i = 0; i < nbReservations; i++) {
                Reservation reservation = iterator.next();

                setValueAt(reservation.getLivre().getTitre(), i, 0);
                setValueAt(reservation.getEtudiant().getId(), i, 1);
                setValueAt(DateUtils.toString(reservation.getDate_res()), i, 2);
                setValueAt(DateUtils.toString(reservation.getDate_fin_res()), i, 3);
            }
        }
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }

    public Class getColumnClass(int i) {
        if (i == 1)
            return Integer.class;
        return String.class;
    }

    public Reservation getReservationByRow(int row) {
        try {
            String stringDate = (String) getValueAt(row, 2);
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            int idEtudiant = (Integer) getValueAt(row, 1);
            int idLivre = Livre.getLivre((String) getValueAt(row, 0)).getId();
            return Reservation.getReservation(date, idEtudiant, idLivre);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
