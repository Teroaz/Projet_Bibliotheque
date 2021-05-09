package model;

import utils.DateUtils;

import java.util.Date;

public class Emprunt {

    private Date date_emp;
    private Date date_fin_emp;
    private Etudiant etudiant;
    private Exemplaire exemplaire;

    public Emprunt(Date date_emp, Etudiant etudiant, Exemplaire exemplaire) {
        this.date_emp = date_emp;
        this.date_fin_emp = DateUtils.ajouterJours(date_emp, 5);
        this.etudiant = etudiant;
        this.exemplaire = exemplaire;
    }

}
