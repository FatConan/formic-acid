package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.math.BigDecimal;

public class BigDecimalExtractor extends AbstractExtractor<BigDecimal> implements IExtract<BigDecimal>{
    @Override
    protected String parsingErrorText(){
        return "This should be a decimal number";
    }

    @Override
    public ParsableValue<BigDecimal> extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return ParsableValue.empty();
        }
        if(node.isBigDecimal()){
            return ParsableValue.of(true, node.decimalValue());
        }
        if(node.isInt() || node.isBigInteger()){
            return ParsableValue.of(true, node.decimalValue());
        }
        if(node.isTextual()){
            try{
                return ParsableValue.of(true, BigDecimal.valueOf(Double.parseDouble(node.asText())));
            }catch(NumberFormatException e){
                return ParsableValue.invalid();
            }
        }
        return ParsableValue.invalid();
    }
}


