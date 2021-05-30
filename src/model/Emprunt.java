package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.DateUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class Emprunt {

    private Date date_emp;
    private Date date_fin_emp;
    private Etudiant etudiant;
    private Exemplaire exemplaire;
    public static HashMap<Integer, Emprunt> emprunt = new HashMap<>();

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

    public static void ajoutEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO EMPRUNT VALUES (" + emprunt.date_emp +", "+ emprunt.date_fin_emp+", "+ emprunt.etudiant.getId() + ", " + emprunt.exemplaire.getId_ex() + ")";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionEmprunt(Emprunt emprunt) {
        String sql = "DELETE FROM EMPRUNT WHERE ID_ET="+ emprunt.etudiant.getId() +" AND ID_EX="+ emprunt.exemplaire.getId_ex();
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }
}
