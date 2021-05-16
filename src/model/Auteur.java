package model;

import model.exceptions.AuteurException;

import java.util.ArrayList;
import java.util.Arrays;

public class Auteur {

    private String prenom;
    private String nom;

    private ArrayList<Livre> livres = new ArrayList<>();

    public static ArrayList<Auteur> auteurs = new ArrayList<>();

    public Auteur(String nom, String prenom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    static public Auteur stringToAuteur(String nomString) throws AuteurException {
        String[] parties = Arrays.stream(nomString.split(",")).map(String::trim).toArray(String[]::new);

        String nom = parties[0];
        String prenom = parties[1];

        if (nom == null || prenom == null) {
            throw new AuteurException("L'auteur ne possède pas de nom ou de prénom.");
        }

        Auteur auteur = null;

        for (Auteur aut : auteurs) {
            if (aut.nom.equalsIgnoreCase(nom) && aut.prenom.equalsIgnoreCase(prenom)) {
                auteur = aut;
                break;
            }
        }

        if (auteur == null) {
            auteur = new Auteur(nom, prenom);
            auteurs.add(auteur);
        }

        return auteur;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", catalogue=" + livres +
                '}';
    }
}
