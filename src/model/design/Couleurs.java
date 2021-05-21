package model.design;

import java.awt.*;

public enum Couleurs {
    BLEU_CLAIR(new Color(160, 172, 242)),
    BLEU_FONCE(new Color(40, 52, 89)),
    BEIGE(new Color(217, 171, 145)),
    BRUN(new Color(115, 59, 47)),
    MARRON_FONCE(new Color(38, 6, 3));

    private final Color couleur;

    Couleurs(Color color) {
        this.couleur = color;
    }

    public Color getCouleur() {
        return couleur;
    }
}
