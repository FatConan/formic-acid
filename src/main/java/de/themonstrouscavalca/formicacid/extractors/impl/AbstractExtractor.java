package de.themonstrouscavalca.formicacid.extractors.impl;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractExtractor<T> implements IExtract<T>{
    protected IntermediateValidateOptional<T> runValidators(Optional<T> value, Collection<IValidate<T>> validators){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>();
        intermediate.setValidatedValue(value);
        for(IValidate<T> v: validators){
            intermediate.merge(v.getValidatedValue(value));
        }
        return intermediate;
    }

    public IntermediateValidateOptional<T> runAdditionalValidator(IntermediateValidateOptional<T> inter, Optional<T> value, IValidate<T> validator){
        inter.setValidatedValue(value);
        inter.merge(validator.getValidatedValue(value));
        return inter;
    }

    /* These methods need to be overridden, but they should not be publicly accessible */
    protected abstract Optional<T> extractValueFromJson(JsonNode node);

    protected Optional<T> extractValueFromJson(String fieldName, JsonNode node){
        Optional<T> value = Optional.empty();
        if(node.has(fieldName) && node.get(fieldName) != null){
            value = extractValueFromJson(node.get(fieldName));
        }
        return value;
    }

    protected boolean missing(JsonNode node) {
        String text = node.asText();
        return text == null || text.trim().isEmpty();
    }

    @Override
    public IntermediateValidateOptional<T> extractFinalIntermediate(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        Optional<T> value = extractValueFromJson(fieldName, node);
        return this.runValidators(value, validators);
    }

    @Override
    public ValidatedOptional<T> extractValidatedValue(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        return new ValidatedOptional<>(extractFinalIntermediate(fieldName, node, validators));
    }
}
