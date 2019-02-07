package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;

import java.util.*;

public abstract class AbstractMarshaller<T> {
    private boolean errored = false;
    private ObjectNode response;
    private ObjectNode errors;
    private ArrayNode globalErrors;

    public AbstractMarshaller() {
        this.reset();
    }

    public static ObjectNode simpleGlobalErrorResponse(String error){
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.putObject("errors");
        ArrayNode global = response.putArray("global_errors");
        global.add(error);
        return response;
    }

    public static ObjectNode emptyErrorResponse(){
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.putObject("errors");
        response.putArray("global_errors");
        return response;
    }

    public void reset(){
        errored = false;
        response = emptyErrorResponse();
        this.errors = (ObjectNode)response.get("errors");
        this.globalErrors = (ArrayNode)response.get("global_errors");
    }

    public void pullErrors(String nameSpace, AbstractMarshaller marshaller){
        if(marshaller.hasErrors()){
            this.errored = true;
            this.errors.put(nameSpace, marshaller.errors);
            this.globalErrors.addAll(marshaller.globalErrors);
        }
    }

    public void pullErrors(AbstractMarshaller marshaller){
        if(marshaller.hasErrors()){
            this.errored = true;
            Iterator<Map.Entry<String, JsonNode>> iter = marshaller.errors.fields();
            while(iter.hasNext()){
                Map.Entry<String, JsonNode> entry = iter.next();
                this.errors.put(entry.getKey(), entry.getValue());
            }
            this.globalErrors.addAll(marshaller.globalErrors);
        }
    }

    public void addError(String jsonField, String errorMsg){
        this.errored = true;
        this.errors.put(jsonField, errorMsg);
    }


    protected <M> ValidatedOptional<M> extractValue(String fieldName, JsonNode json, IExtract<M> extractor, Collection<IValidate<M>> validators){
        ValidatedOptional<M> value = extractor.extractValidatedValue(fieldName, json, validators);
        this.addErrors(fieldName, value);
        return value;
    }

    protected <M> ValidatedOptional<M> extractValue(String fieldName, JsonNode json, IExtract<M> extractor){
        return this.extractValue(fieldName, json, extractor, Collections.emptyList());
    }

    protected <M> Collection<IValidate<M>> requiredOnly(){
        return Collections.singletonList(
                new RequiredValidator<M>()
        );
    }

    public ArrayNode toJson(Collection<T> entities){
        ArrayNode node = JsonNodeFactory.instance.arrayNode();
        for(T entity : entities) {
            node.add(toJson(entity));
        }
        return node;
    }

    public boolean hasErrors(){
        return errored;
    }

    public JsonNode getErrorResponse(){
        return response;
    }

    public void addGlobalError(String errorMessage){
        this.errored = true;
        this.globalErrors.add(errorMessage);
    }

    public void addErrors(String field, ValidatedOptional validatedOptional){
        if(!validatedOptional.isValid()){
            this.errored = true;
            for(String error: validatedOptional.getErrors()){
                addError(field, error);
            }
        }
    }

    protected void jsonDecodeError(){
        addGlobalError("No JSON object could be decoded");
    }

    public T validateFromPOST(Map<String,String[]> data){
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
        return validateFromJson(toJson);
    }

    public abstract T validateFromJson(JsonNode json);
    public abstract JsonNode toJson(T entity);
}
