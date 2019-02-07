package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.Optional;

public class BooleanExtractor extends AbstractExtractor<Boolean> implements IExtract<Boolean>{
    @Override
    public Optional<Boolean> extractValueFromJson(JsonNode node){
        return this.missing(node) ? Optional.empty() : Optional.of(node.asBoolean());
    }
}
