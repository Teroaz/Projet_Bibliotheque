package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.CollectionUtils;
import utils.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        this.etudiant.getReservations().add(this);
    }

    /**
     * Chargement des reservations en cache
     */
    public static void chargerReservation() {

        try {

            ResultSet result = SQLConnection.getConnection().createStatement().executeQuery("SELECT * FROM RESERV");

            while (result.next()) {
                Date date_res = result.getDate("DATE_RES");
                int idEt = result.getInt("ID_ET");
                int idLivre = result.getInt("ID_LIV");

                Etudiant.chargerEtudiants();
                Etudiant etudiant = Etudiant.getById(idEt);
                Livre.chargerLivres();

                Reservation reservation = new Reservation(date_res, etudiant, Livre.getLivre(idLivre));
                etudiant.getReservations().add(reservation);
                Etudiant.liste.put(etudiant.getId(), etudiant);
            }

            result.close();
        } catch (SQLException | DatabaseException err) {
            err.printStackTrace();
        }
    }

    /**
     * Obtenir les réservations d'un étudiant
     * @param idEtudiant : ID de l'étudiant en question
     * @return liste des réservations
     */
    public static ArrayList<Reservation> getReservationEtudiant(int idEtudiant) {
        ArrayList<Reservation> reservationEtudiant = new ArrayList<>();
        for (Reservation reservation : reservation) {
            if (reservation.etudiant.getId() == idEtudiant)
                reservationEtudiant.add(reservation);
        }
        return reservationEtudiant;
    }

    /**
     * Obtenir une réservation
     * @param dateRes : date de début de réservation
     * @param idEtudiant : ID de l'étudiant ayant réservé le livre
     * @param idLivre : ID du livre réservé
     * @return resrevation
     */
    public static Reservation getReservation(Date dateRes, int idEtudiant, int idLivre) {
        for (Reservation res : reservation) {
            if (res.date_res == dateRes && res.etudiant.getId() == idEtudiant && res.livre.getId() == idLivre)
                return res;
        }
        return null;
    }

    /**
     * Ajout d'une réservation en base de données et en cache
     * @param dateRes : date de début de réservation
     * @param idEtudiant : ID de l'étudiant réservant le livre
     * @param idLivre : ID du livre réservé
     */
    public static void ajoutReservation(Date dateRes, int idEtudiant, int idLivre) {
        Reservation res = new Reservation(dateRes, Etudiant.getById(idEtudiant), Livre.getLivre(idLivre));
        String sql = "INSERT INTO RESERV VALUES ('" + DateUtils.toStringSQL(res.date_res) + "', '" + DateUtils.toStringSQL(res.date_fin_res) + "', " + res.etudiant.getId() + ", " + res.livre.getId() + ")";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Suppression d'une réservation en base de données et en cache
     * @param dateRes : date de début de réservation
     * @param idEtudiant : ID de l'étudiant ayant réservé le livre
     * @param idLivre ; ID du livre réservé
     */
    public static void suppressionReservation(Date dateRes, int idEtudiant, int idLivre) {
        String sql = "DELETE FROM RESERV WHERE ID_ET=" + idEtudiant + " and ID_LIV=" + idLivre + " and DATE_RES='" + DateUtils.toStringSQL(dateRes) + "'";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
        Reservation res = getReservation(dateRes, idEtudiant, idLivre);
        if (res != null)
            reservation.remove(res);
    }

    /**
     * Recherche d'une réservation par le titre du livre
     * @param titre : titre du livre
     * @return liste des réservations correspondantes
     */
    public static ArrayList<Reservation> searchByTitre(String titre) {
        return CollectionUtils.streamToArrayList(reservation.stream().filter(e -> e.livre.getTitre().toLowerCase().startsWith(titre.toLowerCase())));
    }

    /**
     * Recherche d'une réservation par nom de l'étudiant
     * @param nom: nom de l'étudiant
     * @return liste des réservations correspondantes
     */
    public static ArrayList<Reservation> searchByEtudiant(String nom) {
        return CollectionUtils.streamToArrayList(reservation.stream().filter(e -> e.etudiant.getNom().toLowerCase().startsWith(nom.toLowerCase())));
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
