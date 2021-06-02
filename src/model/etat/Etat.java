package model.etat;

public enum Etat {
    NEUF("NEUF"),
    BON("BON"),
    ABIME("ABIME");

    private final String label;

    Etat(String label) {this.label = label;}

    public String getLabel() {
        return label;
    }
}