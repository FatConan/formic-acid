package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;


import java.util.Optional;

public class IntegerExtractor extends AbstractExtractor<Integer> implements IExtract<Integer>{
    @Override
    public Optional<Integer> extractValueFromJson(JsonNode node){
        return this.missing(node) ? Optional.empty() : Optional.of(node.asInt());
    }
}
