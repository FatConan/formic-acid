package de.themonstrouscavalca.formicacid.extractors.defn;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;

/**
 * IExtract provides an interface that defines the validation for a particular field withing a full validated collection.
 * Typically the types of T will be of the order of String, Long, Boolean, LocalDate, Enum, etc. as they will represent
 * attributes of another, more complex object that is validated by checking that each of its attributes contains a valid
 * value (as represented in JSON format).
 *
 * @param <T> the type of field
 */
public interface IExtract<T>{
    IntermediateValidateOptional<T> extractFinalIntermediate(String fieldName, JsonNode node, Collection<IValidate<T>> validators);
    ValidatedOptional<T> extractValidatedValue(String fieldName, JsonNode node, Collection<IValidate<T>> validators);
}
