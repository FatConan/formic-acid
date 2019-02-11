package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;
import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;

import java.util.*;

public class BooleanExtractor extends AbstractExtractor<Boolean> implements IExtract<Boolean>{
    private static final Map<String, Boolean> TRUTHS;
    static {
        Map<String, Boolean> map = new HashMap<>();
        map.put("FALSE", false);
        map.put("False", false);
        map.put("false", false);
        map.put("TRUE", true);
        map.put("True", true);
        map.put("true", true);
        TRUTHS = map;
    }

    @Override
    protected String parsingErrorText(){
        return "This should be true, false, 1, 0 or any of \"True\", \"TRUE\", \"true\", \"False\", \"FALSE\", or \"false\"";
    }

    @Override
    public ParsableValue<Boolean> extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return ParsableValue.empty();
        }

        if(node.isBoolean()){
            return ParsableValue.of(true, node.asBoolean());
        }

        if(node.isInt()){
            int intBool = node.asInt();
            return intBool == 0 || intBool == 1 ? ParsableValue.of(true, node.asBoolean()) : ParsableValue.invalid();
        }

        String boolString = node.asText();
        if(TRUTHS.containsKey(boolString)){
            return ParsableValue.of(true, TRUTHS.get(boolString));
        }
        return ParsableValue.invalid();
    }
}
