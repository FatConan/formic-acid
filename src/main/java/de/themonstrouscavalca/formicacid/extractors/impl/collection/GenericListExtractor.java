package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.ArrayList;
import java.util.List;

public class GenericListExtractor<T> extends AbstractExtractor<List<T>> implements IExtract<List<T>>{
    private final AbstractExtractor<T> subExtractor;

    public <V extends AbstractExtractor<T>> GenericListExtractor(V subExtractor){
        this.subExtractor = subExtractor;
    }

    @Override
    public List<T> extractValueFromJson(JsonNode node){
        if(node.isArray() && node.size() > 0){
            List<T> tList = new ArrayList<>();
            for(JsonNode subNode: node){
                T t = subExtractor.extractValueFromJson(subNode);
                if(t != null) tList.add(t);
            }
            return tList;
        }
        return null;
    }
}
