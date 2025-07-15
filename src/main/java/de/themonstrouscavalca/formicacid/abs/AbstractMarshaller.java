package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.errors.MarshallerErrorMap;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.marshallers.defn.IExportToJson;
import de.themonstrouscavalca.formicacid.marshallers.defn.IValidateForm;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;
import de.themonstrouscavalca.formicacid.validators.impl.basic.RequiredValidator;

import java.util.*;

/** A marshaller is a class that provides methods to both validate a JSON representation of a particular object and
 * export a JSON representation of a particular object. This can be achieved by any class implementing both
 * the IValidateForm and IExportToJson, but the AbstractMarshaller is designed as a more complex version that
 * supports a number of ancillary, but useful functions. These include providing a standardised error
 * response format (in JSON) that can be used with the HTML form representations and with the supporting
 * MalicAcid Javascript library, as well as methods to allow the use of sub-marshallers for complex objects and
 * corresponding forms, including methods to enable namespaced error handling.
 *
 * @param <T>
 */
public abstract class AbstractMarshaller<T> implements IValidateForm<T>, IExportToJson<T>{
    private MarshallerErrorMap errors = new MarshallerErrorMap();

    public AbstractMarshaller() {
        this.reset();
    }

    public static ObjectNode simpleGlobalErrorResponse(String error){
       return MarshallerErrorMap.simpleGlobalErrorResponse(error);
    }

    public static ObjectNode emptyErrorResponse(){
        return MarshallerErrorMap.emptyResponse();
    }

    public void reset(){
        this.errors = new MarshallerErrorMap();
    }

    public MarshallerErrorMap getErrors(){
        return errors;
    }

    public void pullErrors(String nameSpace, AbstractMarshaller marshaller){
        this.errors.mergeErrors(nameSpace, marshaller);
    }

    public void pullErrors(AbstractMarshaller marshaller){
        this.errors.mergeErrors(marshaller);
    }

    public void pullErrorsFromGenerator(String entryIdentifier, AbstractMarshaller marshaller){
        this.errors.mergeErrorsFromGenerator(entryIdentifier, marshaller);
    }

    public void addError(String jsonField, String errorMsg){
        this.errors.addError(jsonField, errorMsg);
    }

    protected <M> ValidatedOptional<M> extractValue(String fieldName, JsonNode json, IExtract<M> extractor, Collection<IValidate<M>> validators){
        ValidatedOptional<M> value = extractor.extractValidatedValue(fieldName, json, validators);
        this.addErrors(fieldName, value);
        return value;
    }

    protected <M> ValidatedOptional<M> extractValue(String fieldName, JsonNode json, IExtract<M> extractor){
        return this.extractValue(fieldName, json, extractor, Collections.emptyList());
    }

    /**
     * A shortcut for providing a generic RequiredValidator for any particular field.
     * @return
     * @param <M>
     */
    protected <M> Collection<IValidate<M>> requiredOnly(){
        return Collections.singletonList(
                new RequiredValidator<M>()
        );
    }

    public boolean hasErrors(){
        return this.errors.isErrored();
    }

    public JsonNode getErrorResponse(){
        return this.errors.getErrorResponse();
    }

    public void addGlobalError(String errorMessage){
        this.errors.addGlobalError(errorMessage);
    }

    public void addErrors(String field, ValidatedOptional validatedOptional){
        this.errors.addErrors(field, validatedOptional);
    }

    protected void jsonDecodeError(){
        addGlobalError("No JSON object could be decoded");
    }
}
