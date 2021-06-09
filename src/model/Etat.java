package model;

import java.util.Arrays;

public enum Etat {
    NEUF("NEUF"),
    BON("BON"),
    ABIME("ABIME");

    private final String label;

    Etat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Etat getEtatFromLabel(String label) {
        return Arrays.stream(values()).filter(it -> it.label.equals(label)).findFirst().orElse(Etat.NEUF);
    }
}