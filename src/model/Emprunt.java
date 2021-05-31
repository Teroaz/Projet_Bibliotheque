package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class Emprunt implements Comparable<Emprunt> {

    private Date date_emp;
    private Date date_fin_emp;
    private Etudiant etudiant;
    private Exemplaire exemplaire;
    public static TreeSet<Emprunt> emprunt = new TreeSet<>();

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

        emprunt.add(this);
    }

    public static void chargerEmprunt() {

        try {
            Statement st1 = SQLConnection.getConnection().createStatement();
            Statement st2 = SQLConnection.getConnection().createStatement();

            ResultSet result = st1.executeQuery("SELECT * FROM EMPRUNT");

            while (result.next()) {
                Date dateEmp = result.getDate("DATE_EMP");
                int idEt = result.getInt("ID_ET");
                int idEx = result.getInt("ID_EX");

                ResultSet resultE = st2.executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_EX=" + idEx);
                resultE.next();
                int idLiv = resultE.getInt("ID_LIV");
                resultE.close();

                Etudiant.chargerEtudiants();
                Livre.chargerLivres();

                Emprunt emprunt = new Emprunt(dateEmp, Etudiant.getById(idEt), new Exemplaire(idEx, Livre.getLivre(idLiv), false, Exemplaire.ETAT.NEUF));
            }

        } catch (SQLException | DatabaseException err) {
            err.printStackTrace();
        }
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

    public static ArrayList<Emprunt> getEmpruntEtudiant(int idEtudiant) {
        ArrayList <Emprunt> empruntEtudiant = new ArrayList<>();
        for (Emprunt emprunt : emprunt) {
            if (emprunt.etudiant.getId() == idEtudiant)
                empruntEtudiant.add(emprunt);
        }
        return empruntEtudiant;
    }

    public static void ajoutEmprunt(Emprunt emp) {
        emprunt.add(emp);
        String sql = "INSERT INTO EMPRUNT VALUES (" + emp.date_emp + ", " + emp.date_fin_emp + ", " + emp.etudiant.getId() + ", " + emp.exemplaire.getId_ex() + ")";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionEmprunt(Emprunt emp) {
        emprunt.remove(emp);
        String sql = "DELETE FROM EMPRUNT WHERE ID_ET=" + emp.etudiant.getId() + " AND ID_EX=" + emp.exemplaire.getId_ex();
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int compareTo(Emprunt emprunt) {
        if (this.date_emp.compareTo(emprunt.date_emp) < 0)
            return -1;
        return 1;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "date_emp=" + date_emp +
                ", date_fin_emp=" + date_fin_emp +
                ", etudiant=" + etudiant +
                ", exemplaire=" + exemplaire +
                '}';
    }
}
