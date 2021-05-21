package model;

public class Exemplaire {

    private int id_ex;
    private Livre livre;
    private boolean estEmprunte;
    private ETAT etat;

    /**
     * Le nom de l'énumération répertoriant les différents états d'un exemplaire
     * Neuf, en bon état ou abîmé
     */
    public enum ETAT {
        NEUF, BON, ABIME
    }

    /**
     * @param id    : ID de l'exemplaire
     * @param livre : livre correspondant à l'exemplaire
     */
    public Exemplaire(int id, Livre livre) {
        this.id_ex = id;
        this.livre = livre;
    }

}
