package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;

import java.time.LocalDate;
import java.util.Optional;

public class LocalDateExtractor extends AbstractExtractor<LocalDate> implements IExtract<LocalDate> {
    @Override
    public Optional<LocalDate> extractValueFromJson(JsonNode node){
        if(!this.missing(node)) {
            Optional<String> valueAsText = Optional.ofNullable(node.asText());
            if (valueAsText.isPresent()){
                try {
                    return Optional.ofNullable(LocalDate.parse(valueAsText.get(), DateFormatters.API_DATE_FORMAT));
                } catch (Exception e){
                    //Logger.info("Unable to parse data", e);
                }
            }
        }
        return Optional.empty();
    }
}
