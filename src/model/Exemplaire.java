package model;

import exceptions.DatabaseException;
import sql.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exemplaire {

    private final int id_ex;
    private final Livre livre;
    private boolean estEmprunte;
    private Etat etat;

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

    public void modifierEtat(Etat etat) {
        this.etat = etat;
        try {
            SQLConnection.getStatement().executeUpdate("UPDATE EXEMPLAIRE SET ETAT="+ etat.getLabel() +" WHERE ID_EX=" + id_ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

    public boolean estEmprunte() {
        String sql = "SELECT * FROM EMPRUNT WHERE ID_EX=";
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery( sql + id_ex);
            if (resultSet.next())
                return true;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean estEmprunte(int idEx) {
        String sql = "SELECT * FROM EMPRUNT WHERE ID_EX=";
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery( sql + idEx);
            if (resultSet.next())
                return true;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int getIdNextExemplaire() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ID_EX FROM EXEMPLAIRE WHERE ID_EX=(SELECT MAX(ID_EX) FROM EXEMPLAIRE)");
            if (resultSet.next())
                return resultSet.getInt("ID_EX") + 1;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    public static ArrayList<Exemplaire> getExemplaireLivre(int idLivre) {
        ArrayList<Exemplaire> listeExemplaires = new ArrayList<>();
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_LIV=" + idLivre);
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");

                listeExemplaires.add(new Exemplaire(idEx, Livre.getLivre(idLivre)));
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeExemplaires;
    }

    public static void ajoutExemplaire (int idLivre) {
        Exemplaire exemplaire = new Exemplaire(Exemplaire.getIdNextExemplaire(), Livre.getLivre(idLivre));
        String sql = "INSERT INTO EXEMPLAIRE VALUES ("+ exemplaire.id_ex +", "+ exemplaire.livre.getId() +", '"+ exemplaire.etat.getLabel() +"')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
            SQLConnection.getConnection().commit();
        }
        catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionExemplaire (int idEx) {
        String sql1 = "DELETE FROM EMPRUNT WHERE ID_EX=" + idEx;
        String sql2 = "DELETE FROM EXEMPLAIRE WHERE ID_EX=" + idEx;
        try {
            SQLConnection.getStatement().executeUpdate(sql1);
            SQLConnection.getStatement().executeUpdate(sql2);
        }
        catch (SQLException throwables) {
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
