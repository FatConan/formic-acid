package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.Optional;

public class LongExtractor extends AbstractExtractor<Long> implements IExtract<Long>{
    @Override
    public Optional<Long> extractValueFromJson(JsonNode node){
        return this.missing(node) ? Optional.empty() : Optional.of(node.asLong());
    }
}
