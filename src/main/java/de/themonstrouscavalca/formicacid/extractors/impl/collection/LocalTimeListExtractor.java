package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.LocalTimeExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.time.LocalTime;
import java.util.List;

public class LocalTimeListExtractor extends AbstractExtractor<List<LocalTime>> implements IExtract<List<LocalTime>>{
    private final GenericListExtractor<LocalTime> genericListExtractor  = new GenericListExtractor<>(new LocalTimeExtractor());

    @Override
    public ParsableValue<List<LocalTime>> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
