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
    private ArrayList<Exemplaire> exemplaires = new ArrayList<>();

    /**
     * HashMap associant un id de livre à une instance du livre
     */
    public static HashMap<Integer, Livre> catalogue = new HashMap<>();

    /**
     * @param id : ID du livre
     * @param titre : titre du livre
     * @param auteur : auteur du livre
     */
    public Livre(int id, String titre, Auteur auteur) {
        this.idLivre = id;
        this.titre = titre;
        this.auteur = auteur;

        catalogue.put(id, this);
    }

    /**
     * Chargement des livres en cache
     */
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

        for (Livre livre : catalogue.values()) {
            livre.chargerExemplaire();
            catalogue.put(livre.idLivre, livre);
        }
    }

    /**
     * Chargement des exmeplaires d'un livre
     */
    public void chargerExemplaire() {
        String sql =  "SELECT * FROM EXEMPLAIRE WHERE ID_LIV=" + idLivre;
        try {
            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                int idEx = resultSet.getInt("ID_EX");

                exemplaires.add(new Exemplaire(idEx, this));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Livre getLivre(Integer idLivre) {
        return catalogue.get(idLivre);
    }

    public static Livre getLivre(String titre) {
        return rechercherLivres(titre).get(0);
    }

    /**
     * Rechecrhe livre en fonction du titre
     * @param titre : titre cherché
     * @return liste des livres correspondant au titre cherché
     */
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
        return exemplaires;
    }

    public boolean disponible() {
        for (Exemplaire exemplaire : exemplaires) {
            if (!exemplaire.estEmprunte())
                return true;
        }
        return false;
    }

    /**
     * Retourne un ID d'exemplaire disponible lors d'un emprunt
     * @param idLiv : ID du livre
     * @return ID d'un exmeplaire disponible
     */
    public static Integer getIdExemplaireDispo(int idLiv) {
        for (Exemplaire exemplaire : catalogue.get(idLiv).exemplaires) {
            if (!exemplaire.estEmprunte()) {
                return exemplaire.getId();
            }
        }
        return null;
//        String sql = "SELECT * FROM EXEMPLAIRE WHERE ID_LIV=" + idLiv;
//        try {
//            ResultSet resultSet = SQLConnection.getStatement().executeQuery(sql);
//            while (resultSet.next()) {
//                int idEx = resultSet.getInt("ID_EX");
//                if (!Exemplaire.estEmprunte(idEx))
//                    return idEx;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
    }

    /**
     * obtenir un ID pour éviter tout conflit lors de l'ajout d'un livre
     * @return nouvel ID
     */
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

    /**
     * Ajout d'un livre dans la base de données et en cache
     * @param titre : titre du livre
     * @param auteur : auteur du livre
     */
    public static void ajoutLivre(String titre, Auteur auteur) {
        Livre livre = new Livre(Livre.getIdNextLivre(), titre, auteur);
        String sql = "INSERT INTO LIVRE VALUES (" + livre.idLivre + ", '" + auteur.auteurBD() + "', '" + titre + "')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Suppression d'unn livre de la base de données et du cache, ainsi que de toutes ses réservations ainsi que ses exmeplaires et emprunts
     * @param idLivre
     */
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
