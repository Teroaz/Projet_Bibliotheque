import model.Emprunt;
import model.Etudiant;
import model.Livre;
import model.Reservation;
import sql.SQLConnection;
import view.FenetreBibliotheque;

import java.sql.SQLException;

public class Bibliotheque {

    public static void main(String[] args) {

        SQLConnection sqlConnection;

        try {
            sqlConnection = new SQLConnection();
            sqlConnection.openConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        Livre.chargerLivres();
        Etudiant.chargerEtudiants();
        Emprunt.chargerEmprunt();
        Reservation.chargerReservation();

        new FenetreBibliotheque();
    }
}
