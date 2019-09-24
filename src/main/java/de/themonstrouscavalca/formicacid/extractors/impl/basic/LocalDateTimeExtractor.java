package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.formatters.PrintableDateFormatter;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeExtractor extends AbstractExtractor<LocalDateTime> implements IExtract<LocalDateTime>{
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeExtractor.class);
    private final PrintableDateFormatter parseFormat;

    public LocalDateTimeExtractor(){
        this.parseFormat = DateFormatters.API_DATE_TIME_FORMAT;
    }

    public LocalDateTimeExtractor(boolean secondsAccuracy){
        if(secondsAccuracy){
            this.parseFormat = DateFormatters.API_DATE_TIME_WITH_SECONDS_FORMAT;
        }else{
            this.parseFormat = DateFormatters.API_DATE_TIME_FORMAT;
        }
    }

    @Override
    protected String parsingErrorText(){
        return String.format("This should be a string in the format %s", this.parseFormat.getPattern());
    }

    @Override
    public ParsableValue<LocalDateTime> extractValueFromJson(JsonNode node) {
        if(!this.missing(node)){
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()) {
                try {
                    return ParsableValue.of(true, LocalDateTime.parse(valueAsText.get(),  this.parseFormat.getFormatter()));
                } catch (Exception e) {
                    logger.error("Unable to parse data", e);
                }
            }
        }else{
            return ParsableValue.empty();
        }
        return ParsableValue.invalid();
    }
}
