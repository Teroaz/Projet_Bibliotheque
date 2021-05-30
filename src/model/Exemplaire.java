package model;

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

}
