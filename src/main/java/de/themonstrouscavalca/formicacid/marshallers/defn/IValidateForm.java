package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

public interface IValidateForm<T>{
    default T validateFromPOST(Map<String,String[]> data){
        ObjectNode toJson = JsonNodeFactory.instance.objectNode();
        for(Map.Entry<String, String[]> entry: data.entrySet()){
            if(entry.getValue().length == 0){
                toJson.put(entry.getKey(), "");
            }else if(entry.getValue().length == 1){
                toJson.put(entry.getKey(), entry.getValue()[0]);
            }else{
                ArrayNode vals = toJson.putArray(entry.getKey());
                for(String v: entry.getValue()){
                    vals.add(v);
                }
            }
        }
        return validateFromJson(toJson);
    }

    T validateFromJson(JsonNode json);
}
