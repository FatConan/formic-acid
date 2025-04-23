package de.themonstrouscavalca.formicacid.translators.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import de.themonstrouscavalca.formicacid.translators.defn.ITranslateFormData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMapToJsonTranslator implements ITranslateFormData<Map<String, String[]>, JsonNode>{
    protected void grabString(ObjectNode node, String key, String[] value){
        if(value == null || value.length == 0){
            node.putNull(key);
        }else if(value.length == 1){
            node.put(key, value[0]);
        }else{
            ArrayNode arrayNode =  node.putArray(key);
            for(String v: value){
                arrayNode.add(v);
            }
        }
    }

    public static Map<String, String[]> inverseTransposeData(Map<String, String[]> data, List<String> fieldNames){
        Map<String, String[]> transposed = new HashMap<>();
        for(String fieldName: fieldNames){
            String[] values = data.get(fieldName);
            for(String val : values){
                transposed.put(fieldName + "_" + val, new String[]{val});
            }
        }
        return transposed;
    }

    public static Map<String, String[]> transposeData(Map<String, String[]> data, List<String> fieldNames){
        Map<String, String[]> transposed = new HashMap<>();
        for(String fieldName: fieldNames){
            Pattern fieldPattern = Pattern.compile("(" + fieldName + ")_([0-9]+)");
            if(data.containsKey(fieldName)){
                transposed.put(fieldName, data.get(fieldName));
            }else{
                List<String> entries = new ArrayList<>();
                for(String key : data.keySet()){
                    Matcher m = fieldPattern.matcher(key);
                    if(m.matches()){
                        entries.add(m.group(2));
                    }
                }

                if(!entries.isEmpty()){
                    String[] finalArr = new String[entries.size()];
                    transposed.put(fieldName, entries.toArray(finalArr));
                }
            }
        }
        return transposed;
    }

    @Override
    public JsonNode translate(Map<String, String[]> data){
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        for(Map.Entry<String, String[]> entry: data.entrySet()){
            this.grabString(node, entry.getKey(), entry.getValue());
        }
        return node;
    }
}
