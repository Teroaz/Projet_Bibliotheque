package model;

import utils.DateUtils;

import java.util.Date;
import java.util.HashMap;

public class Emprunt {

    public static HashMap<Integer, Emprunt> emprunt = new HashMap<>();
    private final Date date_emp;
    private final Date date_fin_emp;
    private final Etudiant etudiant;
    private final Exemplaire exemplaire;

    /**
     * @param date_emp   : la date d'emprunt
     * @param etudiant   : l'étudiant empruntant le livre
     * @param exemplaire : l'exemplaire emprunté
     */
    public Emprunt(Date date_emp, Etudiant etudiant, Exemplaire exemplaire) {
        this.date_emp = date_emp;
        this.date_fin_emp = DateUtils.ajouterJours(date_emp, 5);
        this.etudiant = etudiant;
        this.exemplaire = exemplaire;
    }

    public Date getDate_emp() {
        return date_emp;
    }

    public Date getDate_fin_emp() {
        return date_fin_emp;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }
}
