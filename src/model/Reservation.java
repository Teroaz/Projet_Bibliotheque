package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.TreeSet;

public class Reservation implements Comparable<Reservation> {

    private Date date_res;
    private Date date_fin_res;
    private Etudiant etudiant;
    private Livre livre;
    public static TreeSet<Reservation> reservation = new TreeSet<>();

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

        reservation.add(this);
    }

    public static void chargerReservation() {

        try {

            ResultSet result = SQLConnection.getConnection().createStatement().executeQuery("SELECT * FROM RESERV");

            while (result.next()) {
                Date date_res = result.getDate("DATE_RES");
                int idEt = result.getInt("ID_ET");
                int idLivre = result.getInt("ID_LIV");

                Etudiant.chargerEtudiants();
                Livre.chargerLivres();

                Reservation reservation = new Reservation(date_res, Etudiant.getById(idEt), Livre.getLivre(idLivre));
            }

            result.close();
        } catch (SQLException | DatabaseException err) {
            err.printStackTrace();
        }
    }

    public static void ajoutReservation(Reservation res) {
        reservation.add(res);
        String sql = "INSERT INTO RESERV VALUES (" + res.date_res + ", " + res.date_fin_res + ", " + res.etudiant.getId() + ", " + res.livre.getId() + ")";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionReservation(Reservation res) {
        reservation.add(res);
        String sql = "DELETE FROM RESERV WHERE ID_ET=" + res.etudiant.getId() + " and ID_LIV=" + res.livre.getId();
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int compareTo(Reservation res) {
        if (this.date_res.compareTo(res.date_res) < 0)
            return -1;
        return 1;
    }

    public Date getDate_res() {
        return date_res;
    }

    public Date getDate_fin_res() {
        return date_fin_res;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Livre getLivre() {
        return livre;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date_res=" + date_res +
                ", date_fin_res=" + date_fin_res +
                ", etudiant=" + etudiant +
                ", livre=" + livre +
                '}';
    }
}
