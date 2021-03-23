package com.epam.training.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateUtils {

    private DateUtils() { }

    private static final String INPUT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Parses date from string.
     * @param stringDate string date
     * @return parsed date
     * @throws ParseException if string date is invalid
     */
    public static Date parseDate(final String stringDate)
            throws ParseException {
        var inputDateFormat = new SimpleDateFormat(INPUT_DATE_PATTERN);
        java.util.Date date = inputDateFormat.parse(stringDate);
        return new Date(date.getTime());
    }

    /**
     * Formats date by pattern.
     * @param date date
     * @return string with formatted date
     */
    public static String formatDate(final Date date) {
        var dateFormat = new SimpleDateFormat(INPUT_DATE_PATTERN);
        return dateFormat.format(date);
    }

    /**
     *
     * @return current date
     */
    public static Date getCurrentDate() {
        return new Date(new java.util.Date().getTime());
    }

}
