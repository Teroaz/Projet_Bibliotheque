package model;

import utils.DateUtils;

import java.util.Date;

public class Reservation {

    private Date date_res;
    private Date date_fin_res;
    private Etudiant etudiant;
    private Livre livre;

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
}
