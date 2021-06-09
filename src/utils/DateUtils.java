package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static String toString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static String toStringDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        return dateFormat.format(date);
    }

    public static String toStringHeure(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String toStringSQL(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
