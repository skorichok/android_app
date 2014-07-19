package com.skoryk.gymhelper.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarUtils {
    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.US);
        return sdf.format(new Date());
    }

    public static int getCurrentYearId() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonthId() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getDaysInCurrentMonth() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Calendar mycal = new GregorianCalendar(year, month, day);

        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
