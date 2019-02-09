package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.IntegerExtractor;

import java.util.List;

public class IntegerListExtractor extends AbstractExtractor<List<Integer>> implements IExtract<List<Integer>>{
    private final GenericListExtractor<Integer> genericListExtractor  = new GenericListExtractor<>(new IntegerExtractor());

    @Override
    public List<Integer> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
