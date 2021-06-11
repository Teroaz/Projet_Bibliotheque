package model;

import sql.SQLConnection;
import utils.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Exemplaire {

    private final int id_ex;
    private final Livre livre;
    private boolean estEmprunte;
    private Etat etat;

    private static HashMap<Integer, Exemplaire> exemplaires = new HashMap<>();

    /** Constructeur de Exemplaire
     * @param id    : ID de l'exemplaire
     * @param livre : livre correspondant à l'exemplaire
     */
    public Exemplaire(int id, Livre livre) {
        this.id_ex = id;
        this.livre = livre;
        this.estEmprunte = estEmprunte();
        this.etat = obtenirEtat();

        exemplaires.put(id_ex, this);
        livre.getExemplaires().add(this);
    }

    /**
     * Contructeur de Exemplaire
     * @param id : ID de l'exemplaire
     * @param livre : livre correspondant à l'exemplaire
     * @param etat : etat de l'exemplaire
     */
    public Exemplaire(int id, Livre livre, Etat etat) {
        this.id_ex = id;
        this.livre = livre;
        this.etat = etat;
        this.estEmprunte = estEmprunte();

        exemplaires.put(id_ex, this);
        livre.getExemplaires().add(this);
    }

    /**
     * Chargement des exemplaires en cache
     */
    public static void chargerExemplaire() {
        String sql = "SELECT * FROM EXEMPLAIRE";
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");
                int idLivre = resultSet.getInt("ID_LIV");
                Etat etat = Etat.getEtatFromLabel(resultSet.getString("ETAT"));

                Exemplaire exemplaire = new Exemplaire(idEx, Livre.getLivre(idLivre), etat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modifierEtat(Etat etat) {
        this.etat = etat;
        try {
            SQLConnection.getStatement().executeUpdate("UPDATE EXEMPLAIRE SET ETAT=" + etat.getLabel() + " WHERE ID_EX=" + id_ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Obenir l'état de l'exemplaire
     * @return l'éat
     */
    public Etat obtenirEtat() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ETAT FROM EXEMPLAIRE WHERE ID_EX=" + etat);
            if (resultSet.next())
                return Etat.valueOf(resultSet.getString("ETAT"));
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Etat.NEUF;
    }

    /**
     * Vérifie si un exemplaire est emprunté
     * @return trur or false
     */
    public boolean estEmprunte() {
        String sql = "SELECT * FROM EMPRUNT WHERE ID_EX=    ";
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql + id_ex);
            if (resultSet.next())
                return true;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Obtenir un ID exemplaire pour éviter tout conflit lors d'un ajout
     * @return : nouvel ID
     */
    public static int getIdNextExemplaire() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'EXEMPLAIRE_SEQ'");
            return resultSet.next() ? resultSet.getInt("LAST_NUMBER") : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ArrayList<Exemplaire> getExemplaireLivre(int idLivre) {
        return CollectionUtils.streamToArrayList(exemplaires.values().stream().filter(it -> it.livre.getId() == idLivre));
    }

    /**
     * Ajout d'un exemplaire en cache et dans la base de données
     * @param idLivre : ID du livre correspondant à l'exemplaire
     * @param etat : état de ce nouvel exemplaire
     */
    public static void ajoutExemplaire(int idLivre, Etat etat) {
        Exemplaire exemplaire = new Exemplaire(Exemplaire.getIdNextExemplaire(), Livre.getLivre(idLivre), etat);
        String sql = "INSERT INTO EXEMPLAIRE VALUES (-1" + ", " + exemplaire.livre.getId() + ", '" + exemplaire.etat.getLabel() + "')";
        System.out.println(sql);
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Suppression d'un exemplaire
     * @param idEx : ID de l'exemlaire à supprimer
     */
    public static void suppressionExemplaire(int idEx) {
        String sql1 = "DELETE FROM EMPRUNT WHERE ID_EX=" + idEx;
        String sql2 = "DELETE FROM EXEMPLAIRE WHERE ID_EX=" + idEx;
        try {
            SQLConnection.getStatement().executeUpdate(sql1);
            SQLConnection.getStatement().executeUpdate(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Livre getLivre() {
        return livre;
    }

    public int getId() {
        return id_ex;
    }

    public boolean isEstEmprunte() {
        return estEmprunte;
    }

    public Etat getEtat() {
        return etat;
    }


    /**
     * Le nom de l'énumération répertoriant les différents états d'un exemplaire
     * Neuf, en bon état ou abîmé
     */

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id_ex=" + id_ex +
                ", livre=" + livre +
                ", estEmprunte=" + estEmprunte +
                ", etat=" + etat +
                '}';
    }
}
