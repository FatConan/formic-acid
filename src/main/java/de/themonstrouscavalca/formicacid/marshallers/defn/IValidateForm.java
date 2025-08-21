package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.marshallers.exceptions.NotImplemented;

import java.util.Map;
import java.util.Optional;

/**
 * IValidateForm is an interface intended to provide validation for JSON data. Its intention is to return an
 * optional object of type T upon the successful validation of a corresponding JSON data representation.
 * Furthermore, to provide improved support for raw form data, a default implementation of validate accepting
 * a Map of String to String[] elements is provided that performs an intermediate translation to a JSON
 * representation of the same data and hands that off to the undefined validate method.
 *
 * @param <T> The type of the expected validated object
 */
public interface IValidateForm<T>{
    default Optional<T> validate(Map<String,String[]> data){
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
        return validate(toJson);
    }

    default Optional<T> validate(JsonNode json){
        throw new NotImplemented("Validating form data from JSON is not implemented");
    }
}
