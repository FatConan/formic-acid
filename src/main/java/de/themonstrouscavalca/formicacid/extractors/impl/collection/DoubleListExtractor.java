package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.DoubleExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.util.List;

public class DoubleListExtractor extends AbstractExtractor<List<Double>> implements IExtract<List<Double>>{
    private final GenericListExtractor<Double> genericListExtractor  = new GenericListExtractor<>(new DoubleExtractor());

    @Override
    public ParsableValue<List<Double>> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
