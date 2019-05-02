package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.defn.IHandleValue;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;

public abstract class AbstractSimpleMarshaller<T> extends AbstractMarshaller<T>{
    public abstract T validationSteps(T entity, JsonNode json);
    public abstract T create();

    protected <M> void validateValue(T entity, String fieldName, JsonNode json, IExtract<M> extractor, Collection<IValidate<M>> validators, IHandleValue<ValidatedOptional<M>, T> handleValue){
        ValidatedOptional<M> value = extractor.extractValidatedValue(fieldName, json, validators);
        this.addErrors(fieldName, value);
        if(value.isPresent()){
            handleValue.apply(value, entity);
        }
    }

    @Override
    public T validateFromJson(JsonNode json){
        if(json == null){
            jsonDecodeError();
            return null;
        }

        T entity = this.create();
        this.validationSteps(entity, json);

        if(this.hasErrors()){
            return null;
        }

        return entity;
    }
}
