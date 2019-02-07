package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;

import java.time.LocalTime;
import java.util.Optional;

public class LocalTimeExtractor extends AbstractExtractor<LocalTime> implements IExtract<LocalTime>{
    @Override
    public Optional<LocalTime> extractValueFromJson(JsonNode node){
        if(!this.missing(node)) {
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()) {
                try {
                    return Optional.ofNullable(LocalTime.parse(valueAsText.get(), DateFormatters.API_TIME_FORMAT));
                } catch (Exception e) {
                    //Logger.error("Unable to parse time", e);
                }
            }
        }
        return Optional.empty();
    }
}