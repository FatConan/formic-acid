package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

public class LocalDateExtractor extends AbstractExtractor<LocalDate> implements IExtract<LocalDate> {
    private Logger logger = LoggerFactory.getLogger(LocalDateExtractor.class);


    @Override
    public Optional<LocalDate> extractValueFromJson(JsonNode node){
        if(!this.missing(node)) {
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()){
                try {
                    return Optional.ofNullable(LocalDate.parse(valueAsText.get(), DateFormatters.API_DATE_FORMAT));
                } catch (Exception e){
                    logger.info("Unable to parse data", e);
                }
            }
        }
        return Optional.empty();
    }
}
