package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.DateUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class Reservation {

    private Date date_res;
    private Date date_fin_res;
    private Etudiant etudiant;
    private Livre livre;
    public static HashMap<Integer, Emprunt> reservations = new HashMap<>();

    /**
     * la date de retour de réservation est remplie automatiquement
     *
     * @param date_res : la date de réservation de l'emprunt
     * @param etudiant : l'étudiant réservant le livre
     * @param livre    : le livre réservé
     */
    public Reservation(Date date_res, Etudiant etudiant, Livre livre) {
        this.date_res = date_res;
        this.date_fin_res = DateUtils.ajouterJours(date_res, 5);
        this.etudiant = etudiant;
        this.livre = livre;
    }

    public static void ajoutReservation(Reservation reservation) {
        String sql = "INSERT INTO RESERV VALUES ("+ reservation.date_res +", "+ reservation.date_fin_res +", "+ reservation.etudiant.getId() +", "+ reservation.livre.getId() +")";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionReservation(Reservation reservation) {
        String sql = "DELETE FROM RESERV WHERE ID_ET="+ reservation.etudiant.getId() +" and ID_ET="+ reservation.livre.getId();
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }
}
