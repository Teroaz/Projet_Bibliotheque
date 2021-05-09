package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date ajouterJours(Date date, int nbrJours) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, nbrJours);

        return calendar.getTime();
    }
}
