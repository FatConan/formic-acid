package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.defn.IHandleValue;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class AbstractSimpleMarshaller<T> extends AbstractMarshaller<T>{
    public abstract T validationSteps(T entity, JsonNode json);
    public abstract T create();

    protected <M> void validateValue(T entity, String fieldName, JsonNode json, IExtract<M> extractor, Collection<IValidate<M>> validators, IHandleValue<ValidatedOptional<M>, T> handleValue){
        ValidatedOptional<M> value = extractor.extractValidatedValue(fieldName, json, validators);
        this.addErrors(fieldName, value);
        if(value.isValid() && value.isPresent()){
            handleValue.apply(value, entity);
        }
    }

    protected <M> void validateValue(T entity, String fieldName, JsonNode json, IExtract<M> extractor, IHandleValue<ValidatedOptional<M>, T> handleValue){
        validateValue(entity, fieldName, json, extractor, Collections.emptyList(), handleValue);
    }

    @Override
    public Optional<T> validate(JsonNode json){
        if(json == null){
            jsonDecodeError();
            return Optional.empty();
        }

        T entity = this.create();
        this.validationSteps(entity, json);

        if(this.hasErrors()){
            return Optional.empty();
        }

        return Optional.of(entity);
    }

    @Override
    public T validateFromJson(JsonNode json){
        return validate(json).orElse(null);
    }
}
