import model.*;
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
        for (Livre livre : Livre.catalogue.values())
            livre.chargerExemplaire();
        Etudiant.chargerEtudiants();
        Emprunt.chargerEmprunt();
        Reservation.chargerReservation();
        Exemplaire.chargerExemplaire();

        new FenetreBibliotheque();
    }
}
