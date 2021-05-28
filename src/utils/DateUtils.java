package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * ajouter des jours à une date donnée
     *
     * @param date     : la dote
     * @param nbrJours : nombre de jours à ajouter
     * @return : la date additionnée
     */
    public static Date ajouterJours(Date date, int nbrJours) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, nbrJours);

        return calendar.getTime();
    }
}
