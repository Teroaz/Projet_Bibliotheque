package model;

import exceptions.DatabaseException;
import utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Auteur {

    private final String prenom;
    private final String nom;

    private final ArrayList<Livre> livres = new ArrayList<>();

    private static final ArrayList<Auteur> auteurs = new ArrayList<>();

    /**
     * @param nom:    Nom de l'auteur
     * @param prenom: prenom de l'auteur
     */
    public Auteur(String nom, String prenom) {
        this.prenom = prenom;
        this.nom = nom;

        auteurs.add(this);
    }

    /**
     * Ajout d'un livre
     *
     * @param livre: livre à ajouter
     */
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    /**
     * Transforme un string contenant le nom et le prénom, séparés par une virgule, et retourne l'auteur correspondant
     *
     * @param nomString: prend une chaîne de caractère comprenant le nom, le prénom et le livre de l'auteur
     * @return l'objet auteur abstraction de la classe Auteur
     */
    public static Auteur stringToAuteur(String nomString) throws DatabaseException {
        String[] parties = Arrays.stream(nomString.split(",")).map(String::trim).toArray(String[]::new);

        String nom = parties[0];
        String prenom = parties[1];

        if (nom == null || prenom == null) {
            throw new DatabaseException("L'entrée " + Arrays.toString(parties) + " ne contient pas 2 chaines de caractères séparées par une virgule");
        }

        return Auteur.getAuteur(nom, prenom, true);
    }

    public static Auteur getAuteur(String nom, String prenom, boolean createIfNotExists) {
        for (Auteur aut : auteurs) {
            if (aut.nom.equalsIgnoreCase(nom) && aut.prenom.equalsIgnoreCase(prenom)) {
                return aut;
            }
        }

        return createIfNotExists ? new Auteur(nom, prenom) : null;
    }

    /**
     * Renvoie l'auteur en chaîne de caractères
     *
     * @return chaîne de caractères de l'auteur
     */
    @Override
    public String toString() {
        return "Auteur{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '}';
    }

    public String auteurBD() {
        return nom + ", " + prenom;
    }

    public String auteurNP() {
        return prenom + " " + nom.toUpperCase();
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public static ArrayList<Auteur> getAuteurs() {
        return CollectionUtils.streamToArrayList(auteurs.stream().sorted(Comparator.comparing(auteur -> auteur.nom)));
    }

    public ArrayList<Livre> getLivres() {
        return livres;
    }

    public static ArrayList<Auteur> rechercherAuteurs(String saisie) {
        return CollectionUtils.streamToArrayList(auteurs
                .stream().filter(auteur ->
                        auteur.prenom.toLowerCase().contains(saisie.toLowerCase()) ||
                                auteur.nom.contains(saisie.toLowerCase()) ||
                                (auteur.nom + " " + auteur.prenom).toLowerCase().contains(saisie.toLowerCase()) ||
                                (auteur.prenom + " " + auteur.nom).toLowerCase().contains(saisie.toLowerCase())
                ));
    }
}
