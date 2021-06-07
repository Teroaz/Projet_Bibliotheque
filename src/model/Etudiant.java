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

    private final int id;
    private final String nom;
    private final String prenom;
    private String mdp;
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
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;

        liste.put(id, this);
    }

    public boolean peutEmprunterLivre() {
        return Emprunt.getEmpruntEtudiant(id).size() < 5;
    }

    public void emprunterExemplaire(Exemplaire exemplaire) throws RestrictionException {
        if (!peutEmprunterLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas emprunter + de 5 livres.");
        }

        Emprunt.ajoutEmprunt(new Date(), id, exemplaire.getId());
    }

    public boolean peutReserverLivre() {
        return Reservation.getReservationEtudiant(id).size() < 5;
    }

    public void reserverLivre(Livre livre) throws RestrictionException {
        if (!peutReserverLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas réserver + de 5 catalogues.");
        }

        Reservation.ajoutReservation(new Date(), this.id, livre.getId());
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
        return nom + " " + prenom + " (" + id + ")";
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp(boolean decrypted) {
        return decrypted ? CryptUtils.decrypt(mdp) : mdp;
    }

    public static boolean idExists(int id) {
        return getById(id) != null;
    }

    public boolean validatePassword(String password) {
        return Objects.requireNonNull(CryptUtils.encrypt(password)).equals(mdp);
    }
//
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

    public static void ajoutEtudiant(String nom, String prenom, String email, String mdp) {
        String cryptedPassword = CryptUtils.encrypt(mdp);
        String sql = "INSERT INTO ETUDIANT VALUES (" + -1 + ", '" + nom + "', '" + prenom + "', '" + email + "', '" + cryptedPassword + "')";

        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }

        int id = Etudiant.getCurrentDatabaseID();

        new Etudiant(id, nom, prenom, email, CryptUtils.encrypt(mdp));
    }

    public void setEmail(String email) {
        this.email = email;

        String sql = "UPDATE ETUDIANT SET EMAIL='" + this.email + "' WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setMdp(String plain_text_mdp) {
        this.mdp = CryptUtils.encrypt(plain_text_mdp);

        String sql = "UPDATE ETUDIANT SET MDP='" + this.mdp + "' WHERE ID_ET" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        String sql1 = "DELETE from EMPRUNT WHERE ID_ET=" + this.id;
        String sql2 = "DELETE from RESERV WHERE ID_ET=" + this.id;
        String sql3 = "DELETE from ETUDIANT WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getConnection().createStatement().executeUpdate(sql1);
            SQLConnection.getConnection().createStatement().executeUpdate(sql2);
            SQLConnection.getConnection().createStatement().executeUpdate(sql3);
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
        liste.remove(this.id);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
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

    public static int getCurrentDatabaseID() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'ETUDIANT_SEQ'");
            return resultSet.next() ? resultSet.getInt("LAST_NUMBER") : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
