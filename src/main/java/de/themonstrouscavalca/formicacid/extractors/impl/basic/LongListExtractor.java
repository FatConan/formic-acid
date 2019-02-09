package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LongListExtractor extends AbstractExtractor<List<Long>> implements IExtract<List<Long>>{
    @Override
    public List<Long> extractValueFromJson(JsonNode node){
        if(node.isArray() && node.size() > 0){
            List<Long> longs = new ArrayList<>();
            for(JsonNode subNode: node){
                if(!this.missing(subNode)){
                    longs.add(subNode.asLong());
                }
            }
            return longs;
        }
        return null;
    }
}
