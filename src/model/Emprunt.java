package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.CollectionUtils;
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
        this.date_fin_emp = DateUtils.ajouterJours(date_emp, 15);
        this.etudiant = etudiant;
        this.exemplaire = exemplaire;

        emprunt.add(this);
    }

    /**
     * Chargement des emprunts en cache
     */
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

                Emprunt emprunt = new Emprunt(dateEmp, Etudiant.getById(idEt), new Exemplaire(idEx, Livre.getLivre(idLiv)));
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

    /**
     * Obtenir les emprunts d'un étudiant
     * @param idEtudiant : ID de l'étudiant en question
     * @return liste des emprunts de l'étudiant
     */
    public static ArrayList<Emprunt> getEmpruntEtudiant(int idEtudiant) {
        ArrayList<Emprunt> empruntEtudiant = new ArrayList<>();
        for (Emprunt emprunt : emprunt) {
            if (emprunt.etudiant.getId() == idEtudiant)
                empruntEtudiant.add(emprunt);
        }
        return empruntEtudiant;
    }

    /**
     * Obtenir un emprunt
     * @param dateEmp : date de début de l'emprunt
     * @param idEtudiant ; ID de l'étudiant ayant emprunté
     * @param idExemplaire : ID de l'exemmplaire emprunté
     * @return emprunt
     */
    public static Emprunt getEmprunt(Date dateEmp, int idEtudiant, int idExemplaire) {
        for (Emprunt emp : emprunt) {
            if (emp.getDate_emp() == dateEmp && emp.getEtudiant().getId() == idEtudiant && emp.exemplaire.getId() == idExemplaire)
                return emp;
        }
        return null;
    }

    /**
     * Ajout d'un emprunt dans la base de données et en cache
     * @param dateEmp : date de début de l'emprunt
     * @param idEtudiant ; ID de l'étudiant effectuant l'emprunt
     * @param idExemplaire : ID de l'exemplaire a emprunté
     */
    public static void ajoutEmprunt(Date dateEmp, int idEtudiant, int idExemplaire) {

        try {
            Statement st1 = SQLConnection.getConnection().createStatement();
            Statement st2 = SQLConnection.getConnection().createStatement();

            ResultSet resultEx = st1.executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_EX=" + idExemplaire);
            resultEx.next();
            int idLivre = resultEx.getInt("ID_LIV");
            resultEx.close();

            Exemplaire exemplaire = new Exemplaire(idExemplaire, Livre.getLivre(idLivre));
            Emprunt emp = new Emprunt(dateEmp, Etudiant.getById(idEtudiant), exemplaire);

            String sql = "INSERT INTO EMPRUNT VALUES ('" + DateUtils.toStringSQL(emp.date_emp) + "', '" + DateUtils.toStringSQL(emp.date_fin_emp) + "', " + emp.etudiant.getId() + ", " + emp.exemplaire.getId() + ")";

            st2.executeUpdate(sql);

        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Suppression d'un emprunt dans la base de données et en cache
     * @param dateEmp : date de début de l'emprunt
     * @param idEtudiant : iD de l'étudiant ayant effectué l'emprunt
     * @param idExemplaire  :ID de l'exemplaire emprunté
     */
    public static void suppressionEmprunt(Date dateEmp, int idEtudiant, int idExemplaire) {
        String sql = "DELETE FROM EMPRUNT WHERE ID_ET=" + idEtudiant + " AND ID_EX=" + idExemplaire + " and DATE_EMP='" + DateUtils.toStringSQL(dateEmp) + "'";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Emprunt emp = getEmprunt(dateEmp, idEtudiant, idExemplaire);
        if (emp != null)
            emprunt.remove(emp);
    }

    /**
     * Recherche d'un emprunt por le titre du livre
     * @param titre : titre cherché
     * @return liste des emprunts correspondants
     */
    public static ArrayList<Emprunt> searchByTitre(String titre) {
        return CollectionUtils.streamToArrayList(emprunt.stream().filter(e -> e.exemplaire.getLivre().getTitre().toLowerCase().contains(titre.toLowerCase())));
    }

    /**
     * Recherche d'un emprunt por le nom de l'étudiant
     * @param nom : nom de l'étudiant
     * @return liste des emprunts correspondants
     */
    public static ArrayList<Emprunt> searchByEtudiant(String nom) {
        return CollectionUtils.streamToArrayList(emprunt.stream().filter(e -> e.etudiant.getNom().toLowerCase().startsWith(nom.toLowerCase())));
    }

    /**
     * Obtenir les retards d'meprunts
     * @return liste des emprunts en retard
     */
    public static ArrayList<Emprunt> getRetardsEmprunts() {
        return CollectionUtils.streamToArrayList(emprunt.stream().filter(it -> DateUtils.getDifferenceDays(it.date_fin_emp, new Date()) > 15));
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
