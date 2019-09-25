package de.themonstrouscavalca.formicacid.formatters;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class PrintableDateFormatter{
    private final String pattern;
    private final DateTimeFormatter formatter;

    public PrintableDateFormatter(String pattern){
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public PrintableDateFormatter(String pattern, Locale locale){
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern, locale);
    }

    public PrintableDateFormatter(String pattern, DateTimeFormatter formatter){
        this.pattern = pattern;
        this.formatter = formatter;
    }

    public PrintableDateFormatter(DateTimeFormatter formatter){
        this.pattern = "";
        this.formatter = formatter;
    }

    public static PrintableDateFormatter ofPattern(String pattern, Locale locale){
        return new PrintableDateFormatter(pattern , locale);
    }

    public static PrintableDateFormatter ofPattern(String pattern){
        return new PrintableDateFormatter(pattern);
    }

    public String getPattern(){
        return pattern;
    }

    public DateTimeFormatter getFormatter(){
        return formatter;
    }

    public String format(TemporalAccessor temporal){
        return this.getFormatter().format(temporal);
    }
}
