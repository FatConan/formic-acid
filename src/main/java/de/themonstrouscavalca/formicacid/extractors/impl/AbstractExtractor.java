package de.themonstrouscavalca.formicacid.extractors.impl;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.helpers.PresentValue;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;

public abstract class AbstractExtractor<T> implements IExtract<T>{
    protected IntermediateValidateOptional<T> runValidators(PresentValue<T> presentValue, Collection<IValidate<T>> validators){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>();
        intermediate.setPresentInJson(presentValue.isPresentInJson());
        intermediate.setValidatedValue(presentValue.get());
        for(IValidate<T> v: validators){
            intermediate.merge(v.getValidatedValue(presentValue.get()));
        }
        return intermediate;
    }

    public IntermediateValidateOptional<T> runAdditionalValidator(IntermediateValidateOptional<T> inter, T value, IValidate<T> validator){
        inter.setValidatedValue(value);
        inter.merge(validator.getValidatedValue(value));
        return inter;
    }

    /* These methods need to be overridden, but they should not be publicly accessible */
    protected abstract T extractValueFromJson(JsonNode node);

    protected PresentValue<T> extractValueFromJson(String fieldName, JsonNode node){
        T value = null;
        boolean present = false;
        if(node.has(fieldName) && node.get(fieldName) != null){
            value = extractValueFromJson(node.get(fieldName));
            present = true;
        }
        return new PresentValue<>(present, value);
    }

    /**
     * Examine the provided node as text and determine whether the value we're looking for is present based on whether:
     *  a. The Node is null
     *  b. The value of the node is null
     *  c. The value of the node is not null be evaluates as an empty string
     * @param node A JsonNode
     * @return boolean indicating whether the sought value is missing or not
     */
    protected boolean missing(JsonNode node) {
        if(node == null || node.isNull()){
            return true;
        }
        String text = node.asText();
        return text == null || text.trim().isEmpty();
    }

    protected boolean present(JsonNode node){
        return !this.missing(node);
    }

    @Override
    public IntermediateValidateOptional<T> extractFinalIntermediate(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        PresentValue<T> presentValue = extractValueFromJson(fieldName, node);
        return this.runValidators(presentValue, validators);
    }

    @Override
    public ValidatedOptional<T> extractValidatedValue(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        return new ValidatedOptional<>(extractFinalIntermediate(fieldName, node, validators));
    }
}
