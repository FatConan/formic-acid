package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.StringExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.util.List;

public class StringListExtractor extends AbstractExtractor<List<String>> implements IExtract<List<String>>{
    private final GenericListExtractor<String> genericListExtractor  = new GenericListExtractor<>(new StringExtractor());

    @Override
    public ParsableValue<List<String>> extractValueFromJson(JsonNode node){
       return genericListExtractor.extractValueFromJson(node);
    }
}
