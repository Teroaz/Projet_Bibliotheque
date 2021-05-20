package model;

import model.exceptions.AuteurException;
import sql.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Livre {

    private int idLivre;
    private String titre;
    private Auteur auteur;
    private ArrayList<Exemplaire> exemplaires = new ArrayList<>();

    /**
     * HashMap associant un id de livre à une instance du livre
     */
    public static HashMap<Integer, Livre> catalogue = new HashMap<>();

    public Livre(int id, String titre, Auteur auteur) {
        this.idLivre = id;
        this.titre = titre;
        this.auteur = auteur;
    }

    public Livre() {}

    public static void chargerLivres() {

        try {
            ResultSet result = SQLConnection.getStatement().executeQuery("SELECT * FROM LIVRE");

            while (result.next()) {
                int idLivre = result.getInt("ID_LIV");
                String nomAuteur = result.getString("AUTEUR");
                String titre = result.getString("TITRE");

                Auteur auteur = Auteur.stringToAuteur(nomAuteur);

                Livre livre = new Livre(idLivre, titre, auteur);
                catalogue.put(idLivre, livre);

                auteur.ajouterLivre(livre);
            }
        } catch (SQLException | AuteurException err) {
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

        ArrayList<Livre> resultatsRecherche = new ArrayList<>();

        for (Livre livre : catalogue.values()) {
            if (livre.titre.toLowerCase().contains(titre.toLowerCase())) {
                resultatsRecherche.add(livre);
            }
        }

        return resultatsRecherche;
    }

    public int getIdLivre() {
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

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur + '}';
    }
}
