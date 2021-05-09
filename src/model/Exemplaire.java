package model;

public class Exemplaire {

    private int id_ex;
    private Livre livre;
    private boolean estEmprunte;

    public Exemplaire(int id, Livre livre) {
        new Exemplaire(id, livre, false);
    }

    public Exemplaire(int id, Livre livre, boolean estEmprunte) {
        this.id_ex = id;
        this.livre = livre;
        this.estEmprunte = estEmprunte;
    }

}
