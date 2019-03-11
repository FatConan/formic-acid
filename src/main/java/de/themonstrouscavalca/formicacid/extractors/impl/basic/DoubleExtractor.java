package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.util.Optional;

public class DoubleExtractor extends AbstractExtractor<Double> implements IExtract<Double>{
    @Override
    protected String parsingErrorText(){
        return "This should be a floating point number";
    }

    @Override
    public ParsableValue<Double> extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return ParsableValue.empty();
        }
        if(node.isDouble()){
            return ParsableValue.of(true, node.asDouble());
        }
        if(node.isInt() || node.isBigInteger()){
            return ParsableValue.of(true, node.asDouble());
        }
        if(node.isTextual()){
            try{
                return ParsableValue.of(true, Double.parseDouble(node.asText()));
            }catch(NumberFormatException e){
                return ParsableValue.invalid();
            }
        }
        return ParsableValue.invalid();
    }
}
