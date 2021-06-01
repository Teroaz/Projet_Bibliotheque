package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.StringUtils;

import java.sql.ResultSet;
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
    public Exemplaire(int id, Livre livre) {
        this.id_ex = id;
        this.livre = livre;
        this.estEmprunte = estEmprunte();
        this.etat = obtenirEtat();
    }

    public int getId_ex() {
        return id_ex;
    }

    public void modifierEtat(ETAT etat) {
        this.etat = etat;
        try {
            SQLConnection.getStatement().executeUpdate("UPDATE EXEMPLAIRE SET ETAT="+ StringUtils.etatToString(etat) +" WHERE ID_EX=" + id_ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ETAT obtenirEtat() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ETAT FROM EXEMPLAIRE WHERE ID_EX=" + etat);
            if (resultSet.next())
                return StringUtils.stringToEtat(resultSet.getString("ETAT"));
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ETAT.NEUF;
    }

    public boolean estEmprunte() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ID_ET FROM EMPRUNT WHERE ID_EX=" + this.id_ex);
            resultSet.next();
            if (resultSet.next())
                return true;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int getIdLastExemplaire() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ID_EX FROM EXEMPLAIRE WHERE ID_EX=(SELECT MAX(ID_EX) FROM EXEMPLAIRE)");
            if (resultSet.next())
                return resultSet.getInt("ID_EX");
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static void ajoutExemplaire (int idLivre) {
        Exemplaire exemplaire = new Exemplaire(Exemplaire.getIdLastExemplaire() + 1, Livre.getLivre(idLivre));
        String sql = "INSERT INTO EXEMPLAIRE VALUES ("+ exemplaire.id_ex +", "+ exemplaire.livre.getId() +", '"+ StringUtils.etatToString(exemplaire.getEtat()) +"')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionExemplaire (int idEx) {
        String sql = "DELETE FROM EXEMPLAIRE WHERE ID_EX=" + idEx;
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
