package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.Optional;

public class StringExtractor extends AbstractExtractor<String> implements IExtract<String>{
    @Override
    public Optional<String> extractValueFromJson(JsonNode node){
        String n = node.asText();
        if(n != null){
            n = n.trim();
        }
        return Optional.ofNullable(n);
    }
}
