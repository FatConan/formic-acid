package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.basic.LongExtractor;
import de.themonstrouscavalca.formicacid.extractors.impl.collection.GenericListExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LongListExtractor extends AbstractExtractor<List<Long>> implements IExtract<List<Long>>{
    private final GenericListExtractor<Long> genericListExtractor  = new GenericListExtractor<>(new LongExtractor());

    @Override
    public List<Long> extractValueFromJson(JsonNode node){
        return genericListExtractor.extractValueFromJson(node);
    }
}
