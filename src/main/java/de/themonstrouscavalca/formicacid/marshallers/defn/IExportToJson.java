package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import de.themonstrouscavalca.formicacid.marshallers.exceptions.NotImplemented;

import java.util.Collection;

/**
 * The inverse of validating an object from a JSON representation is to export an valid object as a JSON
 * representation. This is used to translate between a representation that works for an object in code, and
 * something that works for a form.
 *
 * The marshallers provide both a means of validating and exporting and therefore implement both
 * the IValidateForm and IExportToJson interfaces.
 *
 * @param <T> the type of the object to be translated into JSON
 */
public interface IExportToJson<T>{
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
