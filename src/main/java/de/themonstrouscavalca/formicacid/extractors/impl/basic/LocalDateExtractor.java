package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

public class LocalDateExtractor extends AbstractExtractor<LocalDate> implements IExtract<LocalDate> {
    private Logger logger = LoggerFactory.getLogger(LocalDateExtractor.class);

    @Override
    protected String parsingErrorText(){
        return String.format("This should be a string in the format %s", DateFormatters.API_DATE_FORMAT.getPattern());
    }

    @Override
    public ParsableValue<LocalDate> extractValueFromJson(JsonNode node){
        if(!this.missing(node)) {
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()){
                try{
                    return ParsableValue.of(true, LocalDate.parse(valueAsText.get(), DateFormatters.API_DATE_FORMAT.getFormatter()));
                }catch (Exception e){
                    logger.info("Unable to parse data", e);
                }
            }
        }else{
            return ParsableValue.empty();
        }
        return ParsableValue.invalid();
    }
}
