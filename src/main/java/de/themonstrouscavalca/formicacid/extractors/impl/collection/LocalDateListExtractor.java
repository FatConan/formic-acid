package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.LocalDateExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.time.LocalDate;
import java.util.List;

public class LocalDateListExtractor extends AbstractExtractor<List<LocalDate>> implements IExtract<List<LocalDate>>{
    private final GenericListExtractor<LocalDate> genericListExtractor  = new GenericListExtractor<>(new LocalDateExtractor());

    @Override
    public ParsableValue<List<LocalDate>> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
