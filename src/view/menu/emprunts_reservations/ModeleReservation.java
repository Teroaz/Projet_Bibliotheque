package view.menu.emprunts_reservations;

import model.Emprunt;
import model.Reservation;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.Date;

public class ModeleReservation extends DefaultTableModel {

    private final String[] intitulesColonnes = {"Titre", "Etudiant", "Date", "Date fin"};

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
        if (i == 0 || i == 1)
            return String.class;
        return Date.class;
    }

}
