package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeExtractor extends AbstractExtractor<LocalDateTime> implements IExtract<LocalDateTime>{
    private Logger logger = LoggerFactory.getLogger(LocalDateTimeExtractor.class);

    @Override
    protected String parsingErrorText(){
        return String.format("This should be a string in the format %s", DateFormatters.API_DATE_TIME_FORMAT.toString());
    }

    @Override
    public ParsableValue<LocalDateTime> extractValueFromJson(JsonNode node) {
        if(!this.missing(node)){
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()) {
                try {
                    return ParsableValue.of(true, LocalDateTime.parse(valueAsText.get(), DateFormatters.API_DATE_TIME_FORMAT));
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
