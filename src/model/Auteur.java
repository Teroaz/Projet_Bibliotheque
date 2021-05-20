package model;

import model.exceptions.AuteurException;

import java.util.ArrayList;
import java.util.Arrays;

public class Auteur {

    private String prenom;
    private String nom;

    private ArrayList<Livre> livres = new ArrayList<>();

    public static ArrayList<Auteur> auteurs = new ArrayList<>();

    /**
     * CConstructeur de la classe auteur
     *
     * @param nom:    Nom de l'auteur
     * @param prenom: prenom de l'auteur
     */
    public Auteur(String nom, String prenom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    /**
     * Méthode pour ajouter un livre
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
     * @throws AuteurException : exception lorsque la chaine de caractères ne correspond pas à un auteur
     */
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
    /**Méthode qui renvoie l'auteur en chaîne de caractères
     * @return chaîne de caractères de l'auteur
     */
    public String toString() {
        return "Auteur{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '}';
    }
}
