package de.themonstrouscavalca.formicacid.formatters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateFormatters{
    public static final PrintableDateFormatter FULL_DATE_TIME = PrintableDateFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final PrintableDateFormatter HUMAN_READABLE_DATE_TIME = PrintableDateFormatter.ofPattern("EEEE, MMMM d yyyy (HH:mm)");
    public static final PrintableDateFormatter HUMAN_READABLE_DATE = PrintableDateFormatter.ofPattern("EEEE, MMMM d yyyy");
    public static final PrintableDateFormatter HUMAN_READABLE_YEAR_MONTH = PrintableDateFormatter.ofPattern("MMMM yyyy");
    public static final PrintableDateFormatter HUMAN_READABLE_YEAR = PrintableDateFormatter.ofPattern("yyyy");

    public static final PrintableDateFormatter API_DATE_FORMAT = PrintableDateFormatter.ofPattern("yyyy-MM-dd");
    public static final PrintableDateFormatter API_TIME_FORMAT = PrintableDateFormatter.ofPattern("HH:mm");
    public static final PrintableDateFormatter API_TIME_WITH_SECONDS_FORMAT = PrintableDateFormatter.ofPattern("HH:mm:ss");
    public static final PrintableDateFormatter API_DATE_TIME_FORMAT = PrintableDateFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final PrintableDateFormatter API_DATE_TIME_WITH_SECONDS_FORMAT = PrintableDateFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final PrintableDateFormatter DAY_ONLY = PrintableDateFormatter.ofPattern("d");
    public static final PrintableDateFormatter DAY_MONTH = PrintableDateFormatter.ofPattern("d MMMM");

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

    public static LocalDate localDateFromTimestamp(Long ts){
        return Instant.ofEpochMilli(ts).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
