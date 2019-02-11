package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.LocalDateTimeExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.time.LocalDateTime;
import java.util.List;

public class LocalDateTimeListExtractor extends AbstractExtractor<List<LocalDateTime>> implements IExtract<List<LocalDateTime>>{
    private final GenericListExtractor<LocalDateTime> genericListExtractor  = new GenericListExtractor<>(new LocalDateTimeExtractor());

    @Override
    public ParsableValue<List<LocalDateTime>> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
