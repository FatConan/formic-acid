package de.themonstrouscavalca.formicacid.formatters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateFormatters{
    public static final DateTimeFormatter FULL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter HUMAN_READABLE_DATE_TIME = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy (HH:mm)");
    public static final DateTimeFormatter HUMAN_READABLE_DATE = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy");
    public static final DateTimeFormatter HUMAN_READABLE_YEAR_MONTH = DateTimeFormatter.ofPattern("MMMM yyyy");
    public static final DateTimeFormatter HUMAN_READABLE_YEAR = DateTimeFormatter.ofPattern("yyyy");

    public static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter API_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter API_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DAY_ONLY = DateTimeFormatter.ofPattern("d");
    public static final DateTimeFormatter DAY_MONTH = DateTimeFormatter.ofPattern("d MMMM");

    public static String daysAgo(LocalDateTime datetime){
        if(datetime != null){
            LocalDateTime today = LocalDateTime.now();
            long days = DAYS.between(datetime.toLocalDate(), today.toLocalDate());
            return String.format("%d day(s) ago", days);
        }
        return "Not available";
    }

    public static String sinceEpoch(LocalDateTime datetime){
        if(datetime != null){
            return String.format("%d", datetime.atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli());
        }
        return "-1";
    }
}
