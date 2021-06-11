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
    private String nom;
    private String prenom;
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

    /**
     * Vérifie si l'étudiant peut emprunter un livre
     * @return true or false
     */
    public boolean peutEmprunterLivre() {
        return Emprunt.getEmpruntEtudiant(id).size() < 5;
    }

    /**
     * l'étudiant emprunte un livre
     * @param exemplaire : l'exemplaire à emprunter
     * @throws RestrictionException : si l'étudiant à déjà 5 emprrunts
     */
    public void emprunterExemplaire(Exemplaire exemplaire) throws RestrictionException {
        if (!peutEmprunterLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas emprunter + de 5 livres.");
        }

        Emprunt.ajoutEmprunt(new Date(), id, exemplaire.getId());
    }

    /**
     * Vérifie si l'étudiant peut réserver un livre
     * @return true or false
     */
    public boolean peutReserverLivre() {
        return Reservation.getReservationEtudiant(id).size() < 5;
    }

    /**
     * Vérifie si l'étudiant peut emprunter le livre voulu
     * @param livre : livre désiré
     * @return true or false
     */
    public boolean peutReserverLivre(Livre livre) {
        return peutReserverLivre() && reservations.stream().noneMatch(it -> it.getLivre().getId() != livre.getId());
    }

    /**
     * L'étudiant réserve le livre
     * @param livre : livre à réserver
     * @throws RestrictionException : si l'étudiant à déjà 5 réservations
     */
    public void reserverLivre(Livre livre) throws RestrictionException {
        if (!peutReserverLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas réserver + de 5 livres.");
        }

        Reservation.ajoutReservation(new Date(), this.id, livre.getId());
    }

    /**
     * Recherche étudiant par nom
     * @param nom : nom cherché
     * @return liste de tous les étudiants avec nom cherché
     */
    public static ArrayList<Etudiant> searchByName(String nom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.nom.toLowerCase().startsWith(nom.toLowerCase())));
    }

    /**
     * Recherche étudiant par prénom
     * @param prenom : prénom cherché
     * @return liste de tous les étudiants avec prénom cherché
     */
    public static ArrayList<Etudiant> searchByFirstname(String prenom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.prenom.toLowerCase().startsWith(prenom.toLowerCase())));
    }

    /**
     * Recherche étudiant par mail
     * @param mail : mail cherché
     * @return liste de tous les étudiants avec mail cherché
     */
    public static ArrayList<Etudiant> searchByMail(String mail) {
        if (!ValidationUtils.isValidMail(mail)) return null;

        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.email.toLowerCase().startsWith(mail.toLowerCase())));
    }

    /**
     * Recherche par nom et prénom
     * @param nom : nom cherché
     * @param prenom : prénom cherché
     * @return liste de tous les étudiants avec nom et prénom cherché
     */
    public static ArrayList<Etudiant> searchByFullname(String nom, String prenom) {
        return CollectionUtils.intersection(searchByName(nom), searchByFirstname(prenom));
    }

    public static ArrayList<Etudiant> searchByFullnameAndMail(String nom, String prenom, String mail) {
        return CollectionUtils.intersection(searchByFullname(nom, prenom), searchByMail(mail));
    }

    /**
     * Obtenir l'étudiant à partir de son ID
     * @param id
     * @return
     */
    public static Etudiant getById(int id) {
        return liste.get(id);
    }

    /**
     * Obtenir le nom de l'étudiant
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifier le nom de l'étudiant
     * @param nom : nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;

        String sql = "UPDATE ETUDIANT SET NOM='" + this.nom + "' WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Obtenir le prénom de l'étudiant
     * @return prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifier le prénom de l'étudiant
     * @param prenom : nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;

        String sql = "UPDATE ETUDIANT SET PRENOM='" + this.prenom + "' WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void setEmail(String email) {
        this.email = email;

        String sql = "UPDATE ETUDIANT SET EMAIL='" + this.email + "' WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getMdp(boolean decrypted) {
        return decrypted ? CryptUtils.decrypt(mdp) : mdp;
    }

    public void setMdp(String plain_text_mdp) {
        this.mdp = CryptUtils.encrypt(plain_text_mdp);

        String sql = "UPDATE ETUDIANT SET MDP='" + this.mdp + "' WHERE ID_ET=" + this.id;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean idExists(int id) {
        return getById(id) != null;
    }

    public boolean validatePassword(String password) {
        return Objects.requireNonNull(CryptUtils.encrypt(password)).equals(mdp);
    }

    /**
     * Pour rendre le livre emprunté
     * @param titre : titre du libvre emprunté
     */
    public void rendre(String titre) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getExemplaire().getLivre().getTitre().equals(titre)) {
                emprunts.remove(emprunt);
                Emprunt.suppressionEmprunt(emprunt.getDate_emp(), emprunt.getEtudiant().getId(), emprunt.getExemplaire().getId());
                return;
            }
        }
    }

    /**
     * Pour supprimer une réservation
     * @param titre : titre du livre réservé
     */
    public void supprimerReservation(String titre) {
        for (Reservation reservation : reservations) {
            if (reservation.getLivre().getTitre().equals(titre)) {
                reservations.remove(reservation);
                Reservation.suppressionReservation(reservation.getDate_res(), reservation.getEtudiant().getId(), reservation.getLivre().getId());
                return;
            }
        }
    }

//    public ArrayList<Emprunt> getEmprunts() {
//        return emprunts;
//    }
//
//    public ArrayList<Reservation> getReservations() {
//        return reservations;
//    }

    /**
     * chargement des étudiants en cache
     */
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

    /**
     * Ajouter un étudiant dans la base de données et en cache
     * @param nom : nom de l'étudiant
     * @param prenom : prénom de l'étudiant
     * @param email : email de l'étudiant
     * @param mdp : mot de passe de l'étudiant
     */
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

    /**
     * Supprimer un étudiant du cache et de la base de données, ainsi que de tous ses emprunts et réservations
     */
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

    /**
     * Obtenir un ID étudiant à partir d'une séquence pour éviter tout conflit lors d'ajout d'étudiant
     * @return nouvel ID
     */
    public static int getCurrentDatabaseID() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'ETUDIANT_SEQ'");
            return resultSet.next() ? resultSet.getInt("LAST_NUMBER") : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Rechecrhe étudiant
     * @param saisie : caractères tapés
     * @return liste des étudiants correspondant à la recherche
     */
    public static ArrayList<Etudiant> rechercherEtudiants(String saisie) {
        return CollectionUtils.streamToArrayList(liste.values()
                .stream().filter(etudiant ->
                        etudiant.prenom.toLowerCase().contains(saisie.toLowerCase()) ||
                                etudiant.nom.contains(saisie.toLowerCase()) ||
                                (etudiant.nom + " " + etudiant.prenom).toLowerCase().contains(saisie.toLowerCase()) ||
                                (etudiant.prenom + " " + etudiant.nom).toLowerCase().contains(saisie.toLowerCase())
                ));
    }
}
