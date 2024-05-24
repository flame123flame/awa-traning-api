package awa.training.api.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final Locale THAI_LOCALE = new Locale("th", "TH");
    private static final Locale ENGLISH_LOCALE = new Locale("en", "US");

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static final String DEFAULT_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static Date parseStringToDate(String strDate, String pattern, Locale locale) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        return sdf.parse(strDate);
    }

    public static String formatDateToString(Date date, String pattern, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        return sdf.format(date);
    }

    public static LocalDate parseStringToLocalDate(String strDate, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale);
        return LocalDate.parse(strDate, formatter);
    }

    public static String formatLocalDateToString(LocalDate date, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale);
        return date.format(formatter);
    }

    public static LocalDateTime parseStringToLocalDateTime(String strDate, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale);
        return LocalDateTime.parse(strDate, formatter);
    }

    public static String formatLocalDateTimeToString(LocalDateTime date, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale);
        return date.format(formatter);
    }

    public static String convertStringDate(String strDate, String fromPattern, String toPattern, Locale fromLocale, Locale toLocale) {
        try {
            Date date = parseStringToDate(strDate, fromPattern, fromLocale);
            return formatDateToString(date, toPattern, toLocale);
        } catch (ParseException e) {
            logger.error("Error parsing date: {}", e.getMessage());
            return null;
        }
    }
}