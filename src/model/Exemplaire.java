package model;

import exceptions.DatabaseException;
import sql.SQLConnection;

import java.sql.SQLException;

public class Exemplaire {

    private final int id_ex;
    private final Livre livre;
    private boolean estEmprunte;
    private ETAT etat;

    /**
     * @param id    : ID de l'exemplaire
     * @param livre : livre correspondant à l'exemplaire
     */
    public Exemplaire(int id, Livre livre, boolean estEmprunte, ETAT etat) {
        this.id_ex = id;
        this.livre = livre;
        this.estEmprunte = estEmprunte;
        this.etat = etat;
    }

    public int getId_ex() {
        return id_ex;
    }

    public static void ajoutExemplaire (Exemplaire exemplaire) {
        String sql = "INSERT INTO EXEMPLAIRE VALUES ("+ exemplaire.id_ex +", "+ exemplaire.livre.getId() +", '"+ exemplaire.etat +"')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionExemplaire (Exemplaire exemplaire) {
        String sql = "DELETE FROM EXEMPLAIRE WHERE ID_EX=" + exemplaire.id_ex;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
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

    public ETAT getEtat() {
        return etat;
    }

    /**
     * Le nom de l'énumération répertoriant les différents états d'un exemplaire
     * Neuf, en bon état ou abîmé
     */
    public enum ETAT {
        NEUF, BON, ABIME
    }

}
