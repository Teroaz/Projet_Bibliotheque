package model;

import model.exceptions.EtudiantException;

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

    public Etudiant(int id, String nom, String prenom, String email, String mdp) {
        this.id_et = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public void emprunterExemplaire() {

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
