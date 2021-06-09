package model.design;

import java.awt.*;

public enum Couleurs {
    BLEU_CLAIR(new Color(160, 172, 242)),
    BLEU_FONCE(new Color(40, 52, 89)),
    BLEU_SOMBRE(new Color(23, 30, 52)),
    BEIGE(new Color(217, 171, 145)),
    BRUN(new Color(115, 59, 47)),
    MARRON_FONCE(new Color(38, 6, 3)),
    ROUGE(new Color(177, 17, 10)),
    VERT(new Color(108, 231, 102)),
    VIOLET(new Color(168, 80, 153)),
    VIOLET_CLAIR(new Color(227, 179, 220));

    private final Color couleur;

    Couleurs(Color color) {
        this.couleur = color;
    }

    public Color getCouleur() {
        return couleur;
    }
}
