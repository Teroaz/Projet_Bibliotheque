package model;


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
    private final String email;
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
        return emprunts.size() < 5;
    }

    public void emprunterExemplaire(Exemplaire exemplaire) throws RestrictionException {
        if (!peutEmprunterLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas emprunter + de 5 livres.");
        }

        Emprunt emprunt = new Emprunt(new Date(), this, exemplaire);
        emprunts.add(emprunt);
    }

    public boolean peutReserverLivre() {
        return reservations.size() < 5;
    }

    public void reserverLivre(Livre livre) throws RestrictionException {
        if (!peutReserverLivre()) {
            throw new RestrictionException("L'étudiant ne peut pas réserver + de 5 catalogues.");
        }

        Reservation reservation = new Reservation(new Date(), this, livre);
        reservations.add(reservation);
    }

    public static ArrayList<Etudiant> rechercherParNom(String nom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.nom.toLowerCase().startsWith(nom.toLowerCase())));
    }

    public static ArrayList<Etudiant> rechercherParPrenom(String prenom) {
        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.prenom.toLowerCase().startsWith(prenom.toLowerCase())));
    }

    public static ArrayList<Etudiant> rechercherParMail(String mail) {
        if (!ValidationUtils.isValidMail(mail)) return null;

        return CollectionUtils.streamToArrayList(liste.values().stream().filter(e -> e.email.toLowerCase().startsWith(mail.toLowerCase())));
    }

    public static ArrayList<Etudiant> rechercherParNomEtPrenom(String nom, String prenom) {
        return CollectionUtils.intersection(rechercherParNom(nom), rechercherParPrenom(prenom));
    }

    public static ArrayList<Etudiant> rechercherParNomPrenomEtMail(String nom, String prenom, String mail) {
        return CollectionUtils.intersection(rechercherParNomEtPrenom(nom, prenom), rechercherParMail(mail));
    }

    public static Etudiant rechercherParId(int id) {
        return liste.get(id);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId_et() {
        return id_et;
    }

    public String getEmail() {
        return email;
    }

    public boolean validatePassword(String password) {
        return Objects.requireNonNull(CryptUtils.encrypt(password)).equals(mdp);
    }

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

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

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_et=" + id_et +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", emprunts=" + emprunts +
                ", reservations=" + reservations +
                '}';
    }
}
