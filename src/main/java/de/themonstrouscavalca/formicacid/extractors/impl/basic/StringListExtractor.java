package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StringListExtractor extends AbstractExtractor<List<String>> implements IExtract<List<String>>{
    @Override
    public Optional<List<String>> extractValueFromJson(JsonNode node){
        if(node.isArray() && node.size() > 0){
            List<String> strings = new ArrayList<>();
            for(JsonNode subNode: node){
                strings.add(subNode.asText());
            }
            return Optional.ofNullable(strings);
        }
        return Optional.empty();
    }
}
