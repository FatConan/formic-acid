package de.themonstrouscavalca.formicacid.tests.formatters;

import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import org.junit.Test;
import java.time.*;
import static org.junit.Assert.assertEquals;

public class FormattersTest{
    @Test
    public void testDates(){
        assertEquals(LocalDate.of(2018, 1, 1), LocalDate.parse("2018-01-01", DateFormatters.API_DATE_FORMAT.getFormatter()));
        assertEquals(LocalDate.of(2018, 12, 31), LocalDate.parse("2018-12-31", DateFormatters.API_DATE_FORMAT.getFormatter()));
        assertEquals(LocalTime.of(11, 31, 0), LocalTime.parse("11:31", DateFormatters.API_TIME_FORMAT.getFormatter()));
        assertEquals(LocalTime.of(22, 22, 0), LocalTime.parse("22:22", DateFormatters.API_TIME_FORMAT.getFormatter()));
        assertEquals(LocalTime.of(23, 54, 12), LocalTime.parse("23:54:12", DateFormatters.API_TIME_WITH_SECONDS_FORMAT.getFormatter()));
        assertEquals(LocalTime.of(9, 54, 46), LocalTime.parse("09:54:46", DateFormatters.API_TIME_WITH_SECONDS_FORMAT.getFormatter()));
        assertEquals(LocalDateTime.of(2018, 3, 12, 11, 22, 0), LocalDateTime.parse("2018-03-12 11:22", DateFormatters.API_DATE_TIME_FORMAT.getFormatter()));
        assertEquals(LocalDateTime.of(2018, 11, 30, 9, 14, 0), LocalDateTime.parse("2018-11-30 09:14", DateFormatters.API_DATE_TIME_FORMAT.getFormatter()));
        assertEquals(LocalDateTime.of(2018, 9, 20, 4, 3, 22), LocalDateTime.parse("2018-09-20 04:03:22", DateFormatters.API_DATE_TIME_WITH_SECONDS_FORMAT.getFormatter()));
        assertEquals(LocalDateTime.of(2018, 2, 1, 2, 59, 59), LocalDateTime.parse("2018-02-01 02:59:59", DateFormatters.API_DATE_TIME_WITH_SECONDS_FORMAT.getFormatter()));
    }

    @Test
    public void testDaysAgo(){
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(10);
        assertEquals("10 day(s) ago", DateFormatters.daysAgo(localDateTime));

        localDateTime = LocalDateTime.now().minusDays(23);
        assertEquals("23 day(s) ago", DateFormatters.daysAgo(localDateTime));

        localDateTime = LocalDateTime.now().minusDays(177);
        assertEquals("177 day(s) ago", DateFormatters.daysAgo(localDateTime));

        localDateTime = null;
        assertEquals("Not available", DateFormatters.daysAgo(localDateTime));
    }

    @Test
    public void testSinceEpoch(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String sinceEpoch = DateFormatters.sinceEpoch(localDateTime);
        Instant instant = Instant.ofEpochMilli(Long.parseLong(sinceEpoch));
        LocalDateTime fromEpoch = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        assertEquals(fromEpoch, localDateTime);

        localDateTime = null;
        assertEquals("-1", DateFormatters.sinceEpoch(localDateTime));
    }
}
