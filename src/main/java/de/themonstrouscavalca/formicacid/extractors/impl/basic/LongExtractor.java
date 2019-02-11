package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

public class LongExtractor extends AbstractExtractor<Long> implements IExtract<Long>{
    @Override
    protected String parsingErrorText(){
        return "This should be an integer";
    }

    @Override
    public ParsableValue<Long> extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return ParsableValue.empty();
        }
        if(node.isLong() || node.isInt()){
            return ParsableValue.of(true, node.asLong());
        }
        if(node.isTextual()){
            try{
                return ParsableValue.of(true, Long.parseLong(node.asText()));
            }catch(NumberFormatException e){
                return ParsableValue.invalid();
            }
        }
        return ParsableValue.invalid();
    }
}
