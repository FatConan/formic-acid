package de.themonstrouscavalca.formicacid.extractors.defn;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;

public interface IExtract<T>{
    IntermediateValidateOptional<T> extractFinalIntermediate(String fieldName, JsonNode node, Collection<IValidate<T>> validators);
    ValidatedOptional<T> extractValidatedValue(String fieldName, JsonNode node, Collection<IValidate<T>> validators);
}
