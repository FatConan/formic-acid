package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.BooleanExtractor;

import java.util.List;

public class BooleanListExtractor extends AbstractExtractor<List<Boolean>> implements IExtract<List<Boolean>>{
    private final GenericListExtractor<Boolean> genericListExtractor  = new GenericListExtractor<>(new BooleanExtractor());

    @Override
    public List<Boolean> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
