package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.formatters.PrintableDateFormatter;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalTimeExtractor extends AbstractExtractor<LocalTime> implements IExtract<LocalTime>{
    private Logger logger = LoggerFactory.getLogger(LocalTimeExtractor.class);
    private final PrintableDateFormatter parseFormat;

    public LocalTimeExtractor(){
        this.parseFormat = DateFormatters.API_TIME_FORMAT;
    }

    public LocalTimeExtractor(boolean secondsAccuracy){
        if(secondsAccuracy){
            this.parseFormat = DateFormatters.API_TIME_WITH_SECONDS_FORMAT;
        }else{
            this.parseFormat = DateFormatters.API_TIME_FORMAT;
        }
    }

    @Override
    protected String parsingErrorText(){
        return String.format("This should be a string in the format %s", this.parseFormat.getPattern());
    }

    @Override
    public ParsableValue<LocalTime> extractValueFromJson(JsonNode node){
        if(!this.missing(node)) {
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()) {
                try {
                    return ParsableValue.of(true, LocalTime.parse(valueAsText.get(), this.parseFormat.getFormatter()));
                } catch (Exception e) {
                    logger.error("Unable to parse time", e);
                }
            }
        }else{
            return ParsableValue.empty();
        }
        return ParsableValue.invalid();
    }
}