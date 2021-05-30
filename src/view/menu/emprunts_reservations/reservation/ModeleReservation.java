package view.menu.emprunts_reservations.reservation;

import model.Emprunt;
import model.Reservation;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.Date;

public class ModeleReservation extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "N° étudiant", "Date", "Date fin"};

    int nbReservations;

    public ModeleReservation() {
        Collection<Emprunt> reservations = Reservation.reservations.values();

        nbReservations = reservations.size();

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        setRowCount(nbReservations);
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
        if (i == 1)
            return Integer.class;
        return Date.class;
    }
}
