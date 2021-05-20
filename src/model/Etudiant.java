package model;


import java.util.ArrayList;
import java.util.Date;

public class Etudiant {

    private int id_et;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private ArrayList<Emprunt> emprunts;
    private ArrayList<Reservation> reservations;

    /**
     * Constructeur de la classe Etudiant
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
    }

    public boolean peutEmprunterLivre() {
        return emprunts.size() < 5;
    }

    public void emprunterExemplaire(Exemplaire exemplaire) throws EtudiantException {
        if (!peutEmprunterLivre()) {
            throw new EtudiantException("L'étudiant ne peut pas emprunter + de 5 livres.");
        }

        Emprunt emprunt = new Emprunt(new Date(), this, exemplaire);
        emprunts.add(emprunt);
    }

    public boolean peutReserverLivre() {
        return reservations.size() < 5;
    }

    public void reserverLivre(Livre livre) throws EtudiantException {
        if (!peutReserverLivre()) {
            throw new EtudiantException("L'étudiant ne peut pas réserver + de 5 catalogue.");
        }

        Reservation reservation = new Reservation(new Date(), this, livre);
        reservations.add(reservation);
    }
}
