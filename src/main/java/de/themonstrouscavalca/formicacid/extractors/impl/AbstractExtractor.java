package de.themonstrouscavalca.formicacid.extractors.impl;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.helpers.ParsableValue;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.helpers.PresentValue;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;

public abstract class AbstractExtractor<T> implements IExtract<T>{
    protected IntermediateValidateOptional<T> runValidators(PresentValue<T> presentValue, Collection<IValidate<T>> validators){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>();
        intermediate.setPresentInJson(presentValue.isPresentInJson());

        //Check that we have a value in the correct format.
        if(!presentValue.isParsable() && presentValue.isPresentInJson()){
            intermediate.addError(this.parsingErrorText());
            intermediate.setValid(false);
        }

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

    public abstract ParsableValue<T> extractValueFromJson(JsonNode node);

    protected String parsingErrorText(){
        return "This is not in the correct format";
    }

    protected PresentValue<T> extractValueFromJson(String fieldName, JsonNode node){
        T finalValue = null;
        boolean present = false;
        boolean parsable = false;
        if(node.has(fieldName) && node.get(fieldName) != null){
            ParsableValue<T> value = extractValueFromJson(node.get(fieldName));
            present = true;
            parsable = value.isParsable();
            finalValue = value.get();
        }
        return new PresentValue<>(present, parsable, finalValue);
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

        if(!node.isArray() && !node.isObject()){
            String text = node.asText();
            return text == null || text.trim().isEmpty();
        }

        return false;
    }

    protected boolean present(JsonNode node){
        return !this.missing(node);
    }

    @Override
    public IntermediateValidateOptional<T> extractFinalIntermediate(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        PresentValue<T> presentValue = this.extractValueFromJson(fieldName, node);
        return this.runValidators(presentValue, validators);
    }

    @Override
    public ValidatedOptional<T> extractValidatedValue(String fieldName, JsonNode node, Collection<IValidate<T>> validators){
        return new ValidatedOptional<>(extractFinalIntermediate(fieldName, node, validators));
    }
}
