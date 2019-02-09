package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

public class LongExtractor extends AbstractExtractor<Long> implements IExtract<Long>{
    @Override
    public Long extractValueFromJson(JsonNode node){
        return this.missing(node) ? null : node.asLong();
    }
}
