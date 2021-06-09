package model;

import exceptions.DatabaseException;
import sql.SQLConnection;
import utils.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Livre {

    private final int idLivre;
    private String titre;
    private Auteur auteur;
//    private ArrayList<Exemplaire> exemplaires = new ArrayList<>();

    /**
     * HashMap associant un id de livre à une instance du livre
     */
    public static HashMap<Integer, Livre> catalogue = new HashMap<>();

    public Livre(int id, String titre, Auteur auteur) {
        this.idLivre = id;
        this.titre = titre;
        this.auteur = auteur;

        catalogue.put(id, this);
    }

    public static void chargerLivres() {

        try {
            ResultSet result = SQLConnection.getStatement().executeQuery("SELECT * FROM LIVRE");

            while (result.next()) {
                int idLivre = result.getInt("ID_LIV");
                String nomAuteur = result.getString("AUTEUR");
                String titre = result.getString("TITRE");

                Auteur auteur = Auteur.stringToAuteur(nomAuteur);

                Livre livre = new Livre(idLivre, titre, auteur);

                auteur.ajouterLivre(livre);
            }

            result.close();
        } catch (SQLException | DatabaseException err) {
            err.printStackTrace();
        }
    }

    public static Livre getLivre(Integer idLivre) {
        return catalogue.get(idLivre);
    }

    public static Livre getLivre(String titre) {
        return rechercherLivres(titre).get(0);
    }

    public static ArrayList<Livre> rechercherLivres(String titre) {
        return CollectionUtils.streamToArrayList(Livre.catalogue.values().stream().filter(livre -> livre.titre.toLowerCase().contains(titre.toLowerCase())));
    }

    public int getId() {
        return idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public ArrayList<Exemplaire> getExemplaires() {
        return Exemplaire.getExemplaireLivre(idLivre);
//        return exemplaires;
    }

    public boolean disponible() {
        String sql1 = "SELECT * FROM EXEMPLAIRE WHERE ID_LIV=" + idLivre;
        String sql2 = "SELECT * FROM RESERV WHERE ID_LIV=" + idLivre;
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql1);
            int exemplairesDispo = 0;
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");
                Exemplaire exemplaire = new Exemplaire(idEx, this);
                if (!exemplaire.estEmprunte())
                    exemplairesDispo++;
            }
            resultSet.close();
            int reservations = 0;
            if (exemplairesDispo > 0) {
                ResultSet resultSet2 = SQLConnection.getStatement().executeQuery(sql2);
                while (resultSet2.next())
                    reservations++;
                resultSet2.close();
            }
            if (exemplairesDispo == 0 || exemplairesDispo <= reservations)
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static Integer getIdExemplaireDispo(int idLiv) {
        String sql = "SELECT * FROM EXEMPLAIRE WHERE ID_LIV=" + idLiv;
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");
                if (!Exemplaire.estEmprunte(idEx))
                    return idEx;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int getIdNextLivre() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT ID_LIV FROM LIVRE WHERE ID_LIV=(SELECT MAX(ID_LIV) FROM LIVRE)");
            if (resultSet.next())
                return resultSet.getInt("ID_LIV") + 1;
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    public static void ajoutLivre(String titre, Auteur auteur) {
        Livre livre = new Livre(Livre.getIdNextLivre(), titre, auteur);
        String sql = "INSERT INTO LIVRE VALUES (" + livre.idLivre + ", '" + auteur.auteurBD() + "', '" + titre + "')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionLivre(int idLivre) {
        String sql1 = "SELECT ID_EX FROM EXEMPLAIRE WHERE ID_LIV=" + idLivre;
        String sql2 = "DELETE FROM RESERV WHERE ID_LIV=" + idLivre;
        String sql3 = "DELETE FROM LIVRE WHERE ID_LIV=" + idLivre;
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql1);
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");
                Exemplaire.suppressionExemplaire(idEx);
            }
            resultSet.close();
            SQLConnection.getConnection().createStatement().executeUpdate(sql2);
            SQLConnection.getConnection().createStatement().executeUpdate(sql3);
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }
        catalogue.remove(idLivre);
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur + '}';
    }

    public static int getCurrentDatabaseID() {
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'LIVRE_SEQ'");
            return resultSet.next() ? resultSet.getInt("LAST_NUMBER") : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
