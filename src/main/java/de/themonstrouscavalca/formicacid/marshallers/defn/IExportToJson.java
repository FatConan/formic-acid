package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import de.themonstrouscavalca.formicacid.marshallers.exceptions.NotImplemented;

import java.util.Collection;

public interface IExportToJson <T>{
    default JsonNode toJson(T entity){
        throw new NotImplemented("Exporting to JSON is not implemented");
    }

    default JsonNode toJson(Collection<T> entities){
        ArrayNode node = JsonNodeFactory.instance.arrayNode();
        for(T entity : entities) {
            node.add(toJson(entity));
        }
        return node;
    }
}
