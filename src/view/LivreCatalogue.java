package view;

import model.Livre;

import javax.swing.*;

public class LivreCatalogue extends JPanel {

    private Livre livre;

    public LivreCatalogue(Livre livre) {
        this.livre = livre;

    }


    public Livre getLivre() {
        return livre;
    }
}
