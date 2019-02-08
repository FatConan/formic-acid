package de.themonstrouscavalca.formicacid.validators.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntermediateValidateOptional<T>{
    private List<String> errors;
    private boolean valid;
    private boolean presentInJson;
    private T validatedValue;

    public IntermediateValidateOptional(){
        this(null);
    }

    public IntermediateValidateOptional(T initialValue){
        this.errors = new ArrayList<>();
        this.valid = true;
        this.validatedValue = initialValue;
    }

    public boolean isValid(){
        return valid;
    }

    public void setValid(boolean valid){
        this.valid = valid;
    }

    public T getValidatedValue(){
        return validatedValue;
    }

    public void setValidatedValue(T validatedValue){
        this.validatedValue = validatedValue;
    }

    public boolean isPresentInJson(){
        return presentInJson;
    }

    public void setPresentInJson(boolean presentInJson){
        this.presentInJson = presentInJson;
    }

    public List<String> getErrors(){
        return errors;
    }

    public void setErrors(List<String> errors){
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public void addErrors(List<String> errors){
        this.errors.addAll(errors);
    }

    public void merge(IntermediateValidateOptional<T> validated){
        this.setValid(this.isValid() && validated.isValid());
        this.setPresentInJson(validated.isPresentInJson());
        this.addErrors(validated.getErrors());
        this.validatedValue = validated.getValidatedValue();
    }
}
