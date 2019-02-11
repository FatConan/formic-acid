package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

public class IntegerExtractor extends AbstractExtractor<Integer> implements IExtract<Integer>{
    @Override
    protected String parsingErrorText(){
        return "This should be an integer";
    }

    @Override
    public ParsableValue<Integer> extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return ParsableValue.empty();
        }

        if(node.isInt()){
            return ParsableValue.of(true, node.asInt());
        }

        if(node.isLong()){
            if(node.asLong() <= Integer.MAX_VALUE && node.asLong() >= Integer.MIN_VALUE){
                return ParsableValue.of(true, node.asInt());
            }else{
                return ParsableValue.invalid();
            }
        }

        if(node.isTextual()){
            try{
                return ParsableValue.of(true, Integer.parseInt(node.asText()));
            }catch(NumberFormatException e){

            }
        }

        return ParsableValue.invalid();
    }
}
