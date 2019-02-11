package de.themonstrouscavalca.formicacid.extractors.impl.collection;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.util.ArrayList;
import java.util.List;

public class GenericListExtractor<T> extends AbstractExtractor<List<T>> implements IExtract<List<T>>{
    private final AbstractExtractor<T> subExtractor;

    public <V extends AbstractExtractor<T>> GenericListExtractor(V subExtractor){
        this.subExtractor = subExtractor;
    }

    @Override
    public ParsableValue<List<T>> extractValueFromJson(JsonNode node){
        if(node.isArray() && node.size() > 0){
            boolean parsable = true;
            List<T> tList = new ArrayList<>();
            for(JsonNode subNode : node){
                ParsableValue<T> t = subExtractor.extractValueFromJson(subNode);
                if(!t.isNull()){
                    tList.add(t.get());
                }
                if(!t.isParsable()){
                    parsable = false;
                }
            }
            return ParsableValue.of(parsable, tList);
        }
        return ParsableValue.empty();
    }
}
