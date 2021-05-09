package model;

import utils.DateUtils;

import java.util.Date;

public class Reservation {

    private Date date_res;
    private Date date_fin_res;
    private Etudiant etudiant;
    private Livre livre;

    public Reservation(Date date_res, Etudiant etudiant, Livre livre) {
        this.date_res = date_res;
        this.date_fin_res = DateUtils.ajouterJours(date_res, 5);
        this.etudiant = etudiant;
        this.livre = livre;
    }
}
