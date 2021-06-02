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

    public static void ajoutLivre(Livre livre) {
        catalogue.put(livre.idLivre, livre);
        String sql = "INSERT INTO LIVRE VALUES (" + livre.idLivre +", '"+ livre.auteur.auteurBD() +"', '"+ livre.titre + "')";
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void suppressionLivre(Livre livre) {
        catalogue.remove(livre.idLivre);
        String sql = "DELETE FROM LIVRE WHERE ID_LIV=" + livre.idLivre;
        try {
            SQLConnection.getStatement().executeUpdate(sql);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur + '}';
    }
}
