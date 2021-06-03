package model;


import exceptions.DatabaseException;
import exceptions.RestrictionException;
import sql.SQLConnection;
import utils.CollectionUtils;
import utils.CryptUtils;
import utils.ValidationUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Etudiant {

    private final int id_et;
    private final String nom;
    private final String prenom;
    private final String mdp;
    private String email;
    private final ArrayList<Emprunt> emprunts = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public static final HashMap<Integer, Etudiant> liste = new HashMap<>();

    /**
     * @param id     : l'ID de l'étudiant
     * @param nom    : le nom de l'étudiant
     * @param prenom : le prénom de l'étudiant
     * @param email  : l'email de l'étudiant
     * @param mdp    : le mot de passe de l'étudiant
     */
    public Etudiant(int id, String nom, String prenom, String email, String mdp) {
        this.id_et = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;

        liste.put(id, this);
    }

    public boolean peutEmprunterLivre() {
        return Emprunt.getEmpruntEtudiant(id_et).size() < 5;
    }

    public void emprunterExemplaire(Exemplaire exemplaire) throws RestrictionException {
        if (!peutEmprunterLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas emprunter + de 5 livres.");
        }

        Emprunt.ajoutEmprunt(new Date(), id_et, exemplaire.getId());
    }

    public boolean peutReserverLivre() {
        return Reservation.getReservationEtudiant(id_et).size() < 5;
    }

    public void reserverLivre(Livre livre) throws RestrictionException {
        if (!peutReserverLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas réserver + de 5 catalogues.");
        }

        Reservation.ajoutReservation(new Date(), this.id_et, livre.getId());
    }

    public static ArrayList<Etudiant> searchByName(String nom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.nom.toLowerCase().startsWith(nom.toLowerCase())));
    }

    public static ArrayList<Etudiant> searchByFirstname(String prenom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.prenom.toLowerCase().startsWith(prenom.toLowerCase())));
    }

    public static ArrayList<Etudiant> searchByMail(String mail) {
        if (!ValidationUtils.isValidMail(mail)) return null;

        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.email.toLowerCase().startsWith(mail.toLowerCase())));
    }

    public static ArrayList<Etudiant> searchByFullname(String nom, String prenom) {
        return CollectionUtils.intersection(searchByName(nom), searchByFirstname(prenom));
    }

    public static ArrayList<Etudiant> searchByFullnameAndMail(String nom, String prenom, String mail) {
        return CollectionUtils.intersection(searchByFullname(nom, prenom), searchByMail(mail));
    }

    public static Etudiant getById(int id) {
        return liste.get(id);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNomPrenomId() {
        return nom + " " + prenom + " (" + id_et + ")";
    }

    public int getId() {
        return id_et;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp(boolean decrypted) {
        return decrypted ? CryptUtils.decrypt(mdp) : mdp;
    }

    public static boolean isIdExistant(int id) {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT * FROM ETUDIANT");
            while (resultSet.next()) {
                if (resultSet.getInt("ID_ET") == id)
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean validatePassword(String password) {
        return Objects.requireNonNull(CryptUtils.encrypt(password)).equals(mdp);
    }

//    public ArrayList<Emprunt> getEmprunts() {
//        return emprunts;
//    }
//
//    public ArrayList<Reservation> getReservations() {
//        return reservations;
//    }

    public static void chargerEtudiants() {
        try {
            ResultSet result = SQLConnection.getStatement().executeQuery("SELECT * FROM ETUDIANT");

            while (result.next()) {
                int idEtudiant = result.getInt("ID_ET");
                String nom = result.getString("NOM");
                String prenom = result.getString("PRENOM");
                String email = result.getString("EMAIL");
                String mdp = result.getString("MDP");

                new Etudiant(idEtudiant, nom, prenom, email, mdp);
            }

            result.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void ajoutEtudiant(int idEt, String nom, String prenom, String email, String mdp) {
        Etudiant e = new Etudiant(idEt, nom, prenom, email, CryptUtils.encrypt(mdp));
        String sql = "INSERT INTO ETUDIANT VALUES (" + idEt + ", '" + nom + "', '" + prenom + "', '" + email + "', '" + e.mdp + "')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void modificationEtudiant(int idEt, String email) {
        String sql = "UPDATE ETUDIANT SET EMAIL='" + email + "' WHERE ID_ET=" + idEt;
        System.out.println(sql);
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Etudiant etudiant = liste.get(idEt);
        etudiant.email = email;
        liste.put(idEt, etudiant);
    }

    public static void suppressionEtudiant(int idEt) {
        String sql1 = "DELETE from EMPRUNT WHERE ID_ET=" + idEt;
        String sql2 = "DELETE from RESERV WHERE ID_ET=" + idEt;
        String sql3 = "DELETE from ETUDIANT WHERE ID_ET=" + idEt;
        try {
            SQLConnection.getConnection().createStatement().executeUpdate(sql1);
            SQLConnection.getConnection().createStatement().executeUpdate(sql2);
            SQLConnection.getConnection().createStatement().executeUpdate(sql3);
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
        liste.remove(idEt);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_et=" + id_et +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
//                ", emprunts=" + emprunts +
//                ", reservations=" + reservations +
                '}';
    }

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
