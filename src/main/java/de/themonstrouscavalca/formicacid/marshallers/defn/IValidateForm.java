package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.marshallers.exceptions.NotImplemented;

import java.util.Map;
import java.util.Optional;

public interface IValidateForm<T>{
    default Optional<T> validateFromPOST(Map<String,String[]> data){
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

    default Optional<T> validateFromJson(JsonNode json){
        throw new NotImplemented("Validating form data from JSON is not implemented");
    }
}
