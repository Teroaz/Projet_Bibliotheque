package utils;

import model.Exemplaire;

public class StringUtils {

    public static String etatToString(Exemplaire.ETAT etat) {
        if (etat == Exemplaire.ETAT.NEUF)
            return "NEUF";
        if (etat == Exemplaire.ETAT.BON)
            return "BON";
        else
            return "ABIME";
    }

    public static Exemplaire.ETAT stringToEtat(String etat) {
        if (etat == "NEUF")
            return Exemplaire.ETAT.NEUF;
        if (etat == "BON")
            return Exemplaire.ETAT.BON;
        else
            return Exemplaire.ETAT.ABIME;
    }
}
