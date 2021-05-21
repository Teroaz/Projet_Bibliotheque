package model.design;

import javax.swing.*;

public enum Icones {
    REPERTOIRE_ETUDIANTS(new ImageIcon(getChemin() + "repertoire.png", "répertoire_étudiants"));

    private final ImageIcon icone;

    Icones(ImageIcon image_icon) {
        this.icone = image_icon;
    }

    public static String getChemin() {
        return "src/resources/icons/";
    }

    public ImageIcon getIcone() {
        return icone;
    }
}
