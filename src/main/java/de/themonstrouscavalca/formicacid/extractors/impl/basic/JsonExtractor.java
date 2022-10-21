package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

public class JsonExtractor extends AbstractExtractor<JsonNode> implements IExtract<JsonNode>{
    @Override
    public ParsableValue<JsonNode> extractValueFromJson(JsonNode node){
        return this.missing(node) ? ParsableValue.empty() : ParsableValue.of(true, node);
    }
}
