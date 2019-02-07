package de.themonstrouscavalca.formicacid.validators.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntermediateValidateOptional<T>{
    private List<String> errors;
    private boolean valid;
    private Optional<T> validatedValue;

    public IntermediateValidateOptional(){
        this(Optional.empty());
    }

    public IntermediateValidateOptional(Optional<T> initialValue){
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

    public Optional<T> getValidatedValue(){
        return validatedValue;
    }

    public void setValidatedValue(Optional<T> validatedValue){
        this.validatedValue = validatedValue;
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
        this.addErrors(validated.getErrors());
        this.validatedValue = validated.getValidatedValue();
    }
}
