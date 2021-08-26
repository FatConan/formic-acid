package de.themonstrouscavalca.formicacid.errors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.themonstrouscavalca.formicacid.abs.AbstractMarshaller;
import de.themonstrouscavalca.formicacid.errors.defn.IMapErrorFields;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.*;

public class MarshallerErrorMap{
    private static final MarshallerErrorMap EMPTY = new MarshallerErrorMap();
    private static final ObjectNode EMPTY_RESPONSE = EMPTY.getErrorResponse();

    public static MarshallerErrorMap empty(){
        return EMPTY;
    }
    public static ObjectNode emptyResponse(){
        return EMPTY_RESPONSE;
    }
    public static ObjectNode simpleGlobalErrorResponse(String error){
        MarshallerErrorMap temp = new MarshallerErrorMap();
        temp.addGlobalError(error);
        return temp.getErrorResponse();
    }

    private Map<ErrorNamespace, Map<String, List<String>>> namespacedFieldErrors;
    private List<String> globalErrors;
    private boolean errored = false;

    public MarshallerErrorMap(){
        this.namespacedFieldErrors = new HashMap<>();
        this.globalErrors = new ArrayList<>();
    }

    public Map<ErrorNamespace, Map<String, List<String>>> getNamespacedFieldErrors(){
        return namespacedFieldErrors;
    }

    public List<String> getGlobalErrors(){
        return globalErrors;
    }

    public boolean isErrored(){
        return errored;
    }

    public ObjectNode getErrorResponse(){
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        ObjectNode fieldErrors = response.putObject("errors");
        for(ErrorNamespace ns: this.namespacedFieldErrors.keySet()){
            Map<String, List<String>> currentFieldErrors = this.namespacedFieldErrors.get(ns);
            ObjectNode currentErrorSpace = fieldErrors;
            if(!ns.isDefaultNameSpace()){
                currentErrorSpace = fieldErrors.putObject(ns.getNamespace());
            }
            for(String field: currentFieldErrors.keySet()){
                currentErrorSpace.put(field, String.join("\n", currentFieldErrors.get(field)));
            }
        }
        ArrayNode globalErrorArray = response.putArray("global_errors");
        for(String error: this.globalErrors){
            globalErrorArray.add(error);
        }
        return response;
    }

    public boolean hasFieldError(String field){
        return this.hasFieldError(ErrorNamespace.getDefault(), field);
    }

    public boolean hasFieldError(ErrorNamespace ns, String field){
        return this.namespacedFieldErrors.getOrDefault(ns, Collections.emptyMap()).containsKey(field);
    }

    public void addGlobalError(String errorMessage){
        this.errored = true;
        this.globalErrors.add(errorMessage);
    }

    public void addError(String jsonField, String errorMsg){
        this.addError(ErrorNamespace.getDefault(), jsonField, errorMsg);
    }

    public void addError(ErrorNamespace ns, String jsonField, String errorMsg){
        this.errored = true;
        if(this.namespacedFieldErrors.containsKey(ns)){
            if(this.namespacedFieldErrors.get(ns).containsKey(jsonField)){
                this.namespacedFieldErrors.get(ns).get(jsonField).add(errorMsg);
            }else{
                this.namespacedFieldErrors.get(ns).put(jsonField, new ArrayList<>(Collections.singletonList(errorMsg)));
            }
        }else{
            Map<String, List<String>> fieldErrors = new HashMap<>();
            fieldErrors.put(jsonField, new ArrayList<>(Collections.singletonList(errorMsg)));
            this.namespacedFieldErrors.put(ns, fieldErrors);
        }
    }

    public void addErrors(String field, ValidatedOptional validatedOptional){
        if(!validatedOptional.isValid()){
            for(String error: validatedOptional.getErrors()){
                this.addError(field, error);
            }
        }
    }

    private void mergeNamespacedErrors(ErrorNamespace ns, AbstractMarshaller marshaller, IMapErrorFields mapper){
        if(marshaller.hasErrors()){
            this.errored = true;
            Map<String, List<String>> fieldErrors =  marshaller.getErrors().getNamespacedFieldErrors()
                    .getOrDefault(ErrorNamespace.getDefault(), Collections.emptyMap());

            Map<String, List<String>> mappedFieldErrors = new HashMap<>();
            for(String field: fieldErrors.keySet()){
                String mappedField = mapper.map(field);
                mappedFieldErrors.put(mappedField, fieldErrors.get(field));
            }

            if(this.namespacedFieldErrors.containsKey(ns)){
                for(String field: mappedFieldErrors.keySet()){
                    List<String> errorMsgs = mappedFieldErrors.get(field);
                    if(this.namespacedFieldErrors.get(ns).containsKey(field)){
                        this.namespacedFieldErrors.get(ns).get(field).addAll(errorMsgs);
                    }else{
                        this.namespacedFieldErrors.get(ns).put(field, new ArrayList<>(errorMsgs));
                    }
                }
            }else{
                this.namespacedFieldErrors.put(ns, fieldErrors);
            }
            this.globalErrors.addAll(marshaller.getErrors().getGlobalErrors());
        }
    }

    public void mergeErrors(String namespace, AbstractMarshaller marshaller){
        this.mergeNamespacedErrors(ErrorNamespace.of(namespace), marshaller, (s) -> (s));
    }

    public void mergeErrors(AbstractMarshaller marshaller){
        this.mergeNamespacedErrors(ErrorNamespace.getDefault(), marshaller, (s) -> (s));
    }

    public void mergeErrorsFromGenerator(String entryIdentifier, AbstractMarshaller marshaller){
        this.mergeNamespacedErrors(ErrorNamespace.getDefault(),
                marshaller,
                (s) -> (String.format("%s_%s", s, entryIdentifier))
        );
    }
}
