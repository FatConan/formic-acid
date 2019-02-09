package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

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
    public Boolean extractValueFromJson(JsonNode node){
        if(this.missing(node)){
            return null;
        }

        if(node.isBoolean()){
            return node.asBoolean();
        }

        if(node.isInt()){
            int intBool = node.asInt();
            return intBool == 0 || intBool == 1 ? node.asBoolean() : null;
        }

        String boolString = node.asText();
        return TRUTHS.getOrDefault(boolString, null);
    }
}
