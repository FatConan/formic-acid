package de.themonstrouscavalca.formicacid.translators.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.translators.defn.ITranslateFormData;

import java.util.Map;

public class StringMapToJsonTranslator implements ITranslateFormData<Map<String, String[]>, JsonNode>{
    protected void grabString(ObjectNode node, String key, String[] value){
        if(value.length == 1){
            node.put(key, value[0]);
        }else if(value.length == 0 || value == null){
            node.putNull(key);
        }else{
            ArrayNode arrayNode =  node.putArray(key);
            for(String v: value){
                arrayNode.add(v);
            }
        }
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
